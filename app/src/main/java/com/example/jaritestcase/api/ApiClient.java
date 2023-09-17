package com.example.jaritestcase.api;

import android.content.Context;

import androidx.annotation.NonNull;

import com.chuckerteam.chucker.api.ChuckerInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://api.pokemontcg.io/v2/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {

        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new ChuckerInterceptor.Builder(context).build());
            httpClient.addInterceptor(new Interceptor() {
                @NonNull
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .header("X-Api-Key", "f2a74334-781d-44ee-8d74-da599df5d27c")
                            .build();

                    return chain.proceed(newRequest);
                }
            });

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
