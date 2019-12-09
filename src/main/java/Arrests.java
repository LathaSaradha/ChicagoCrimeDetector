import java.io.Reader;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Arrests implements Wait {
    public Map<String, Integer> getArrests() {
        return arrests;
    }

    public Map<String, Integer> arrests;

    public Arrests() {
        this.arrests = new LinkedHashMap<>();
        // this.getCrimeTypes();
        this.generateArrestCounts();
        this.filterHashMap();
    }

    public Reader sendQuery(String query) {
        // String query;
        // query = "$select=distinct(primary_type) as crime_types";
        SendHttpRequest sendHttpRequest = new SendHttpRequest(query);
        return sendHttpRequest.sendHttpRequest();
    }

    public List<String> getCrimeTypes() {
        String query;
        query = "$select=distinct(primary_type) as crime_types";
        Reader reader = this.sendQuery(query);
        String requestedFieldName;
        final List<String> crimeTypes;
        requestedFieldName = "crime_types";
        crimeTypes = JsonParser.getCrimeTypes(reader, requestedFieldName);
        System.out.println(crimeTypes);
        return crimeTypes;
    }

    public void generateArrestCounts() {
        String queryFirstPart = "$select=count(id) as arrests where primary_type=\"";
        String queryLastPart = "\" and arrest=true";

        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(50);
            for (String crimeType : getCrimeTypes()) {
                service.submit(() -> {

                    System.out.println("check " + crimeType);
                    Reader reader = this.sendQuery(queryFirstPart + crimeType + queryLastPart);
                    generateArrestCountsHelper(reader, crimeType);
                });

            }
        } finally {
            //Usage of abstract method in Wait interface.

            wait(service, 4);
        }


/*
        getCrimeTypes().parallelStream()
                .forEach((e)->{
           // System.out.println("in lambda");
            Reader reader = sendQuery(queryFirstPart+e+queryLastPart);
            generateArrestCountsHelper(reader,e);
        });

 */
    }

    public void generateArrestCountsHelper(Reader reader, String crimeType) {
        //System.out.println("In helper");
        String requestFieldName = "arrests";
        int arrestCount;
        arrestCount = JsonParser.crimeCounter(reader, requestFieldName);
        //System.out.println(arrestCount);
        this.arrests.put(crimeType, arrestCount);

    }

    public void filterHashMap() {
        LinkedHashMap<String, Integer> temp = new LinkedHashMap<>();
        this.getArrests()
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach((e) -> temp.put(e.getKey(), e.getValue()));
        System.out.println(temp);
        this.arrests = temp;

    }

    @Override
    public void wait(ExecutorService service, int seconds) {
        try {
            if (service != null) {
                service.awaitTermination(4, TimeUnit.SECONDS);

                if (service.isTerminated()) {
                    System.out.println(this.getArrests());

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
