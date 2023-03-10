package com.healthpoint.medic.network;

import com.healthpoint.medic.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static final String BASE_URL = "https://healthpoint.id/hpkader/api/";
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static final OkHttpClient client = buildClient();
    private static final Retrofit retrofit = buildRetrofit(client);

    private static OkHttpClient buildClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request.Builder builder = request.newBuilder()
                                .addHeader("Accept", "application/x-www-form-urlencoded")
                                .addHeader("Connection", "open");

                        request = builder.build();
                        return chain.proceed(request);
                    }
                });
        if (BuildConfig.DEBUG) {
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        return builder.build();
    }

    private static Retrofit buildRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T createService(Class<T> service) {
        return retrofit.create(service);
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
