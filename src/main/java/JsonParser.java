import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.Reader;

public class JsonParser
{
    public static int crimeCounter(Reader reader,String requestedFieldName)
    {
        int counter=0;
        try (JsonReader jsonReader = new JsonReader(reader)) {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                String fieldName;
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    fieldName = jsonReader.nextName();
                    if (fieldName.equals(requestedFieldName)) {
                        counter = Integer.parseInt(jsonReader.nextString());
                        System.out.println("Receiving Data");
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




    public static int crimeCounterTotal(Reader reader,String requestedFieldName)
    {
        int counter=0;
        try (JsonReader jsonReader = new JsonReader(reader)) {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                String fieldName;
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    fieldName = jsonReader.nextName();
                    if (fieldName.equals(requestedFieldName)) {
                        counter++;
                       // counter = Integer.parseInt(jsonReader.nextString());
                        System.out.println(jsonReader.nextString());
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




}
