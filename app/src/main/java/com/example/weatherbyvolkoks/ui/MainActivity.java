package com.example.weatherbyvolkoks.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherbyvolkoks.BaseActivity;
import com.example.weatherbyvolkoks.BuildConfig;
import com.example.weatherbyvolkoks.MyApplicationForRetrofit;
import com.example.weatherbyvolkoks.data.LoaderWeatherRetrofit;
import com.example.weatherbyvolkoks.data.Parcel;
import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.Soc.SocSourceBuilder;
import com.example.weatherbyvolkoks.data.Soc.SocialDataSource;
import com.example.weatherbyvolkoks.data.API.WeatherRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.weatherbyvolkoks.R.*;

public class MainActivity extends BaseActivity {
    private static String citys = "Moscow";
    private final static int REQUEST_CODE = 1;
    private final static int SETTING_CODE = 2;

    private LoaderWeatherRetrofit loaderWeatherRetrofit;
    private TextView city;
    private TextView temperature;
    private TextView description;
    private TextView temp_max_min;
    private ImageView iconWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);
        SocialDataSource sourceData = new SocSourceBuilder().setResources(getResources()).build();
        initGUI();
        initRecyclerView(sourceData);
        initRetrofit();
        requestRetrofit(citys, BuildConfig.WEATHER_API_KEY);

    }

    private void initGUI() {
        city = findViewById(id.City);
        temperature = findViewById(id.Temperature);
        description = findViewById(id.weather_description);
        iconWeather = findViewById(id.iconWeatherView);
        temp_max_min = findViewById(id.temp_max_min);
    }

    private void initRetrofit() {
        Retrofit retrofit = MyApplicationForRetrofit.getCreateRetrofit();
        loaderWeatherRetrofit = retrofit.create(LoaderWeatherRetrofit.class);
    }

    private void requestRetrofit(String cityName, String keyApi) {
        loaderWeatherRetrofit.loadWeather(citys, BuildConfig.WEATHER_API_KEY)
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            String valueCity = response.body().getName();
                            int valueTemperature = (int) response.body().getMain().getTemp();
                            String valueDescription = response.body().getWeathers()[0].getDescription();
                            int valueTempMax = (int) response.body().getMain().getTemp_max();
                            int valueTempMin = (int) response.body().getMain().getTemp_min();

                            city.setText(valueCity);
                            temperature.setText(String.format(valueTemperature + "\u2103"));
                            description.setText(valueDescription);
                            temp_max_min.setText(String.format("%d/%d", valueTempMax, valueTempMin));

                            if (response.body().getWeathers()[0].getMain().equals("Clouds")) {
                                iconWeather.setImageDrawable(getDrawable(drawable.overcast));
                            } else if (response.body().getWeathers()[0].getMain().equals("Rain")) {
                                iconWeather.setImageDrawable(getDrawable(drawable.showers));
                            } else if (response.body().getWeathers()[0].getMain().equals("Snow")) {
                                iconWeather.setImageDrawable(getDrawable(drawable.snows));
                            } else if (response.body().getWeathers()[0].getMain().equals("Clear")) {
                                iconWeather.setImageDrawable(getDrawable(drawable.cleare));
                            } else if (response.body().getWeathers()[0].getMain().equals("Drizzle")) {
                                iconWeather.setImageDrawable(getDrawable(drawable.showersscattered));
                            } else if (response.body().getWeathers()[0].getMain().equals("Thunderstorm")) {
                                iconWeather.setImageDrawable(getDrawable(drawable.violentstorm));
                            } else {
                                iconWeather.setImageDrawable(getDrawable(drawable.severealert));
                            }
                        }
                        if (!response.isSuccessful() && response.errorBody() != null) {
                            try {
                                JSONObject jsonError = new JSONObject(response.errorBody().string());
                                String error = jsonError.getString("message");
                                ADError("Ошибка JSON", error);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, final Throwable t) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("ERROR")
                                        .setMessage(t.getMessage());
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        });
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), ScreenSetting.class);
                startActivityForResult(intent, SETTING_CODE);
                break;
            case R.id.enter_city_selection2:
                Intent intent2 = new Intent(getApplicationContext(), CitySelectionScreen.class);
                startActivityForResult(intent2, REQUEST_CODE);
                break;
            case R.id.refresh_the_weather:
                requestRetrofit(citys, BuildConfig.WEATHER_API_KEY);
                break;
            case R.id.about_app:
                initAlertDialogAboutApp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView(SocialDataSource data) {

        RecyclerView recyclerView = findViewById(id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        WeatherAdapter adapter = new WeatherAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_CODE) {
            recreate();
        }
        if (requestCode == REQUEST_CODE && data != null) {
            if (resultCode == RESULT_OK) {
                Parcel parcel = (Parcel) data.getSerializableExtra("parcel");
                city.setText(parcel.cityName);
                citys = parcel.weatherCityName;
                requestRetrofit(citys, BuildConfig.WEATHER_API_KEY);

            }
        }
    }

    public void ADError(String title, String e) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(title)
                        .setMessage(e);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void initAlertDialogAboutApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(string.about_app)
                .setMessage(string.about_app_message)
                .setCancelable(false)
                .setPositiveButton(string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Спасибо что выбрали нас!)", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
