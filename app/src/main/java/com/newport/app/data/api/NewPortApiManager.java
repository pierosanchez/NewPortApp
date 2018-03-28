package com.newport.app.data.api;

import android.util.Log;

import com.newport.app.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tohure on 06/11/17.
 */

public class NewPortApiManager {

    private static NewPortApi newPortApi;

    private NewPortApiManager() {
        throw new IllegalStateException("Api Manager");
    }

    public static NewPortApi apiManager() {

        if (newPortApi == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(getInterceptor())
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BuildConfig.URL_BASE)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            newPortApi = client.create(NewPortApi.class);
        }

        return newPortApi;
    }

    static Retrofit getRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(getInterceptor()).build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.URL_BASE)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return logging;
    }
}
