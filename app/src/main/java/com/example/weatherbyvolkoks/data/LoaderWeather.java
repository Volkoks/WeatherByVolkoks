package com.example.weatherbyvolkoks.data;

import com.example.weatherbyvolkoks.BuildConfig;
import com.example.weatherbyvolkoks.MyApplicationForRetrofit;
import com.example.weatherbyvolkoks.data.API.WeatherRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoaderWeather {
    private LoaderWeatherRetrofit loaderWeatherRetrofit;
    private ILoaderWeather iLoaderWeather;

    public LoaderWeather(ILoaderWeather iLoaderWeather) {
        this.iLoaderWeather = iLoaderWeather;
    }
    public void downloadWeather(String cityName){
        initRetrofit();
        requestRetrofit(cityName);
    }

    private void initRetrofit(){
        Retrofit retrofit = MyApplicationForRetrofit.getCreateRetrofit();
        loaderWeatherRetrofit = retrofit.create(LoaderWeatherRetrofit.class);
    }
    private void requestRetrofit(String cityName) {
        loaderWeatherRetrofit.loadWeather(cityName, "metric", "ru", BuildConfig.WEATHER_API_KEY)
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            iLoaderWeather.activate(response);
                        }
                        if (!response.isSuccessful() && response.errorBody() != null) {
                            try {
                                JSONObject jsonError = new JSONObject(response.errorBody().string());
                                String error = jsonError.getString("message");
                                iLoaderWeather.ADError("Ошибка JSON", error);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                iLoaderWeather.ADError("Ошибка JSON JSONException", e.getMessage());
                            } catch (IOException e) {
                                e.printStackTrace();
                                iLoaderWeather.ADError("Ошибка JSON IOException", e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, final Throwable t) {
                        iLoaderWeather.ADError("ERROR onFailure", t.getMessage());
                    }
                });
    }


}
