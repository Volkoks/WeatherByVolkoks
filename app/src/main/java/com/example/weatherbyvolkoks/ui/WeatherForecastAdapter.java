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
import com.example.weatherbyvolkoks.data.dataWeatherHistoryFor5Day.WeatherForecastDao;
import com.example.weatherbyvolkoks.data.dataWeatherHistoryFor5Day.WeatherForecastFor5Day;
import com.example.weatherbyvolkoks.data.dataWeatherHistoryFor5Day.WeatherForecastSource;

import java.util.List;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {
    private WeatherForecastSource weatherForecast5dayDatabase;
    private Activity activity;
    private WeatherForecastDao weatherForecastDao;

    public WeatherForecastAdapter(WeatherForecastSource weatherForecast5dayDatabase, Activity activity) {
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
        List<WeatherForecastFor5Day> weatherForecast = weatherForecastDao.getAllWeatherForecast();
        WeatherForecastFor5Day weatherForecastFor5Day = weatherForecast.get(position);
        holder.dateAndMonth.setText(weatherForecastFor5Day.date);
        holder.description.setText(weatherForecastFor5Day.description);
        holder.dateAndMonth.setText(weatherForecastFor5Day.temperature);

    }

    @Override
    public int getItemCount() {
        return 5;
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
