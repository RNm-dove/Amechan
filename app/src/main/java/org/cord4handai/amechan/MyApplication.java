package org.cord4handai.amechan;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.cord4handai.amechan.Model.ClaimService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ryosuke on 2018/03/08.
 */

public class MyApplication extends Application {


    private ClaimService claimService;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        // どのActivityからでもAPIを利用できるように、このクラスでAPIを利用する
        setupAPIClient();
    }

    private void setupAPIClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("API LOG", message);
            }
        });

        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://160.16.148.217:3000/claims/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        claimService = retrofit.create(ClaimService.class);
    }

    public ClaimService getClaimService() {
        return claimService;
    }


}
