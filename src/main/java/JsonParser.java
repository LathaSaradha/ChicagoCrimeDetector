import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.Reader;

import java.util.HashMap;

import java.util.Map;


public class JsonParser {
    public static int crimeCounter(Reader reader, String requestedFieldName) {
        String fieldName;
        int counter = 0;
        try (JsonReader jsonReader = new JsonReader(reader)) {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    fieldName = jsonReader.nextName();
                    if (fieldName.equals(requestedFieldName)) {
                        counter = Integer.parseInt(jsonReader.nextString());
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
            }
            jsonReader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }


    public static Map<String, Integer> getArrestCount(Reader reader, String requestedFieldName1, String requestedFieldName2) {
        String fieldName;
        Map<String, Integer> values = new HashMap<>();

        try (JsonReader jsonReader = new JsonReader(reader)) {
            jsonReader.beginArray();

            while (jsonReader.hasNext()) {
                jsonReader.beginObject();

                while (jsonReader.hasNext()) {

                    fieldName = jsonReader.nextName();
                    if (fieldName.equals(requestedFieldName1)) {
                        String type = jsonReader.nextString();
                        String fieldName2 = jsonReader.nextName();
                        if (fieldName2.equals(requestedFieldName2)) {
                            int count = jsonReader.nextInt();
                            values.put(type, count);
                        }


                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
            }
            jsonReader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }


}
