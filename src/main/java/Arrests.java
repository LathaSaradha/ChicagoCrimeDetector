/* This Class is used to find the arrest record data in the Web Page */

import java.io.Reader;
import java.util.Collections;
import java.util.LinkedHashMap;

import java.util.Map;


public class Arrests {
    private Map<String, Integer> arrests;

    public Map<String, Integer> getArrests() {
        return arrests;
    }

    public Arrests() {
        this.arrests = new LinkedHashMap<>();
        this.generateArrestCountForTopFiveCrimes();
    }

    private Reader sendQuery(String query) {
        SendHttpRequest sendHttpRequest = new SendHttpRequest(query);
        return sendHttpRequest.sendHttpRequest();
    }

    private void generateArrestCountForTopFiveCrimes() {
        String queryFirst = "$select=primary_type as type,count(id) as count where arrest=True " +
                "group by primary_type order by count(ID) DESC\n";
        Reader reader = this.sendQuery(queryFirst);
        Map<String, Integer> results = JsonParser.getArrestCount(reader, "type", "count");
        results.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach((e) -> this.arrests.put(e.getKey(), e.getValue()));
    }
}


