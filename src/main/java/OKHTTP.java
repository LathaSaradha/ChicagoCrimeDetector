import com.google.gson.stream.JsonReader;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;


public class OKHTTP
{
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://data.cityofchicago.org/resource/ijzp-q8t2.json?$select=count(id) as total_Crimes where year=2008")
                .get()
                .build();
        int count = 0;
        Response response = client.newCall(request).execute();
        InputStream initialStream = new ByteArrayInputStream(response.body().string().getBytes());
        Reader reader = new InputStreamReader(initialStream);
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.beginArray();
        count = getCount(count, jsonReader);
        jsonReader.endArray();
        System.out.println(count);
       // System.out.println( response.body().string());

    }

    private static int getCount(int count, JsonReader jsonReader) throws IOException {
        while (jsonReader.hasNext()) {
            String name = null;
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                name = jsonReader.nextName();
                if (name.equals("total_Crimes")) {
                    count++;
                    System.out.println(name);
                    System.out.println(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
        }
        return count;
    }
}
