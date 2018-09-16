package ozanturcan.com.myapplication.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class JsonPlaceholderClient {

    private static Retrofit retrofit;
    private static final String base_URL = "https://jsonplaceholder.typicode.com/";

    public static Retrofit getRetrofitBase() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }




}