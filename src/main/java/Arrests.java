import java.io.Reader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Arrests implements Wait {
    private Map<String, Integer> arrests;

    public  Map<String, Integer> getArrests() {
        return arrests;
    }

    public Arrests() {
        this.arrests = new LinkedHashMap<>();
        this.generateArrestCounts();
        this.filterMap();
    }

    public Reader sendQuery(String query) {
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

    public void generateArrestCounts()  {
        String queryFirstPart = "$select=count(id) as arrests where primary_type=\"";
        String queryLastPart = "\" and arrest=true";
        getCrimeTypes().forEach(e->{
            Thread thread = new Thread(()->{
                Reader reader = this.sendQuery(queryFirstPart + e + queryLastPart);
                generateArrestCountsHelper(reader, e);
            });
           thread.start();
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

//        ExecutorService service;
//        service = Executors.newFixedThreadPool(30);
//
//        try {
//           service = Executors.newSingleThreadExecutor();
//            for (String crimeType : getCrimeTypes()) {
//                service.submit(() -> {
//                    Reader reader = this.sendQuery(queryFirstPart + crimeType + queryLastPart);
//                    generateArrestCountsHelper(reader, crimeType);
//                });
//            }
//            service.shutdown();
//        } finally {
//            wait(service, 1);
//        }
    }

    public void generateArrestCountsHelper(Reader reader, String crimeType) {
        String requestFieldName = "arrests";
        int arrestCount;
        arrestCount = JsonParser.crimeCounter(reader, requestFieldName);
        this.arrests.put(crimeType, arrestCount);
    }

    public void filterMap() {
        LinkedHashMap<String, Integer> temp = new LinkedHashMap<>();
        this.getArrests()
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach((e) -> temp.put(e.getKey(), e.getValue()));
        this.arrests = temp;
    }

    @Override
    public void wait(ExecutorService service, int seconds) {
        try {
            if (service != null) {
                service.awaitTermination(4, TimeUnit.SECONDS);

                if (service.isTerminated()) {
                    System.out.println("Service Terminated");

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
