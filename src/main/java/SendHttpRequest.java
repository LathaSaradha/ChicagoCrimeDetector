import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;


public class SendHttpRequest {
    private String URL = "https://data.cityofchicago.org/resource/ijzp-q8t2.json?";
    private final OkHttpClient okHttpClient = new OkHttpClient();

    public SendHttpRequest(String query) {
        this.URL = URL + query;
    }


    public Reader sendHttpRequest() {
        Reader reader = null;
        try {
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
