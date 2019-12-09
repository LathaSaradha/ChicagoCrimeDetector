import java.io.Reader;
import java.util.*;

public class Arrests
{
    public Map<String, Integer> getArrests() {
        return arrests;
    }

    public Map<String,Integer> arrests;

    public Arrests()
    {
       this.arrests = new LinkedHashMap<>();
       this.getCrimeTypes();
       this.generateArrestCounts();
       this.filterHashMap();
    }

    public Reader sendQuery(String query)
    {
       // String query;
       // query = "$select=distinct(primary_type) as crime_types";
        SendHttpRequest sendHttpRequest = new SendHttpRequest(query);
        return sendHttpRequest.sendHttpRequest();
    }

    public List<String> getCrimeTypes()
    {
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

    public void generateArrestCounts()
    {
        String queryFirstPart = "$select=count(id) as arrests where primary_type=\"";
        String queryLastPart = "\" and arrest=true";



        getCrimeTypes().parallelStream()
                .forEach((e)->{
            System.out.println("in lambda");
            Reader reader = sendQuery(queryFirstPart+e+queryLastPart);
            generateArrestCountsHelper(reader,e);
        });
    }

    public void generateArrestCountsHelper(Reader reader, String crimeType)
    {
        System.out.println("In helper");
        String requestFieldName = "arrests";
        int arrestCount;
        arrestCount = JsonParser.crimeCounter(reader,requestFieldName);
        System.out.println(arrestCount);
        this.arrests.put(crimeType,arrestCount);

    }

    public void filterHashMap()
    {
        LinkedHashMap<String,Integer> temp = new LinkedHashMap<>();
                this.getArrests()
                .entrySet()
                .stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach((e)-> temp.put(e.getKey(),e.getValue()));
        System.out.println(temp);
        this.arrests = temp;

    }


    public static void main(String[] args)
    {
     Arrests arrests = new Arrests();
        System.out.println(arrests.arrests);
    }
}
