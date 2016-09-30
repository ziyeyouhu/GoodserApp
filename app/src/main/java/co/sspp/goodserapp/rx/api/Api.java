package co.sspp.goodserapp.rx.api;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  on 16-5-17
 *
 */
public class Api {

    private static ApiService SERVICE;


    public static ApiService getDefault() {
        if (SERVICE == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
            SERVICE = new Retrofit.Builder()
                    .baseUrl("http://mobile.sspp.co/v1.2.1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build().create(ApiService.class);

        }
        return SERVICE;
    }


    /**
     * @param paths
     * @return
     */
    public static Map<String, RequestBody> uploadMany(ArrayList<String> paths) {
        Map<String, RequestBody> photos = new HashMap<>();
        if (paths.size() > 0) {
            for (int i = 0; i < paths.size(); i++) {
                photos.put("file" + i + "\"; filename=\"icon.png", RequestBody.create(MediaType.parse("multipart/form-data"), new File(paths.get(i))));
            }
        }
        return photos;

    }

}
