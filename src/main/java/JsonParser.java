import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public enum JsonParser
{
    ;

    public static int crimeCounter(Reader reader,String requestedFieldName)
    {
        String fieldName;
        int counter=0;
        try (JsonReader jsonReader = new JsonReader(reader)) {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    fieldName = jsonReader.nextName();
                    if (fieldName.equals(requestedFieldName)) {
                        counter = Integer.parseInt(jsonReader.nextString());
                        System.out.println(requestedFieldName+": "+counter);
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


   public static List<String> getCrimeTypes(Reader reader, String requestedFieldName)
   {
       String fieldName;
       List<String> crimeTypes = new ArrayList<>();
       try (JsonReader jsonReader = new JsonReader(reader)) {
           jsonReader.beginArray();
           while (jsonReader.hasNext()) {
               jsonReader.beginObject();
               while (jsonReader.hasNext()) {
                   fieldName = jsonReader.nextName();
                   if (fieldName.equals(requestedFieldName)) {
                       crimeTypes.add(jsonReader.nextString());
                       //System.out.println(crimeTypes.add(jsonReader.nextString()));
                       //System.out.println("Receiving Data");

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
    return crimeTypes;
   }


}
