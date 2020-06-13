package com.example.weatherbyvolkoks;

import android.app.Application;
import com.example.weatherbyvolkoks.data.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplicationForRetrofit extends Application {
    private static Retrofit retrofit;

    public static Retrofit getCreateRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
