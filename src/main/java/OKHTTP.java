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
                .url("https://data.cityofchicago.org/resource/ijzp-q8t2.json?$select=id,date")
                .get()
                .build();

        Response response = client.newCall(request).execute();
        InputStream initialStream = new ByteArrayInputStream(response.body().string().getBytes());
        Reader reader = new InputStreamReader(initialStream);
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            String name = null;
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                name = jsonReader.nextName();
                if (name.equals("id")) {
                    System.out.println(name);
                    System.out.println(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
        }
            jsonReader.endArray();

       // System.out.println( response.body().string());

    }
}
