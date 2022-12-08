package com.example.tab_fragmentos;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static App shareInstance;
    private Api api;

    @Override
    public void onCreate() {
        super.onCreate();

        shareInstance = this;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://065b-2806-269-481-1322-18d-61b3-4225-df80.ngrok.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public static synchronized App getInstance(){
        return shareInstance;
    }

    public Api getApi() {
        return api;
    }
}
