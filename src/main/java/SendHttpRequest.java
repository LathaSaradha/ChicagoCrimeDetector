import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;

public class SendHttpRequest
{
    private  String URL = "https://data.cityofchicago.org/resource/ijzp-q8t2.json?";
    private  String query;

    public SendHttpRequest(String query) { this.URL = URL+query; }


    public  Reader sendHttpRequest()
    {
        System.out.println("Sending HTTP request");
        System.out.println(URL);
        Reader reader = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            builder.url(URL);
            builder.get();
            Request request = builder.build();
            Response response = okHttpClient.newCall(request).execute();
            InputStream inputStream = new ByteArrayInputStream(response.body().string().getBytes());
            reader = new InputStreamReader(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return reader;
    }
}
