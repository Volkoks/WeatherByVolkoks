package com.example.weatherbyvolkoks.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;
import com.example.weatherbyvolkoks.data.dataWeatherHistoryFor5Day.WeatherForecast5dayDatabase;
import com.example.weatherbyvolkoks.data.dataWeatherHistoryFor5Day.WeatherForecastFor5Day;

import java.util.List;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {
    private WeatherForecast5dayDatabase weatherForecast5dayDatabase;
    private Activity activity;

    public WeatherForecastAdapter(WeatherForecast5dayDatabase weatherForecast5dayDatabase, Activity activity) {
        this.weatherForecast5dayDatabase = weatherForecast5dayDatabase;
        this.activity = activity;
    }

    @NonNull
    @Override
    public WeatherForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_for_weather_forecast_5_day, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherForecastAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dayOfWeek;
        private TextView dateAndMonth;
        private ImageView image;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.dayOfWeek);
            dateAndMonth = itemView.findViewById(R.id.dateAndMonth);
            image = itemView.findViewById(R.id.iconWeather);
            description = itemView.findViewById(R.id.description_card_5day);
        }
    }
}
