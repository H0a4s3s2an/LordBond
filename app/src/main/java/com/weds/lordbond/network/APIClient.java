package com.weds.lordbond.network;

import com.weds.lordbond.util.NetworkConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;
    
    public static APIRoute getAPIClient() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(NetworkConstants.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
        }
        return retrofit.create(APIRoute.class);
    }

    public static OkHttpClient getClient() {
    
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .build();
    }
}
