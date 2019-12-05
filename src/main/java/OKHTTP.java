import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class OKHTTP
{
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://data.cityofchicago.org/resource/ijzp-q8t2.csv")
                .get()
//                .addHeader("User-Agent", "PostmanRuntime/7.20.1")
//                .addHeader("Accept", "/")
//                .addHeader("Cache-Control", "no-cache")
//                .addHeader("Postman-Token", "ca6eb5f1-d0fd-40f4-9905-aff7ee38b6ea,b0bf6fda-a78f-4e95-b6f3-dc342bd8fdce")
//                .addHeader("Host", "data.cityofchicago.org")
//                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
