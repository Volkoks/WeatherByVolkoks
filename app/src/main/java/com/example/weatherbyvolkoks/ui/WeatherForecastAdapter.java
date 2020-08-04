package com.example.weatherbyvolkoks.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.WeatherAPI_1day.WeatherRequest;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.ListWeather;
import com.squareup.picasso.Picasso;

import retrofit2.Response;


public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {

    int temperature;
    ImageView iconWeather;
    private ListWeather[] listWeathers;

    public WeatherForecastAdapter(ListWeather[] listWeathers) {
        this.listWeathers = listWeathers;
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
        temperature = (int) listWeathers[position].getMain().getTemp();
        iconWeather = holder.image;
        holder.dateAndMonth.setText(listWeathers[position].getDt_txt());
        holder.description.setText(listWeathers[position].getWeather()[0].getDescription());
        holder.dayOfWeek.setText(temperature + "\u2103");
        initImageIcon(listWeathers[position]);
    }

    private void initImageIcon(ListWeather listWeather) {
        String main = listWeather.getWeather()[0].getMain();
        switch (main) {
            case "Clouds":
                Picasso.get().load(R.drawable.overcast).into(iconWeather);
                break;
            case "Rain":
                Picasso.get().load(R.drawable.showers).into(iconWeather);
                break;
            case "Snow":
                Picasso.get().load(R.drawable.snows).into(iconWeather);
                break;
            case "Clear":
                Picasso.get().load(R.drawable.cleare).into(iconWeather);
                break;
            case "Drizzle":
                Picasso.get().load(R.drawable.showersscattered).into(iconWeather);
                break;
            case "Thunderstorm":
                Picasso.get().load(R.drawable.violentstorm).into(iconWeather);
                break;
            default:
                Picasso.get().load(R.drawable.severealert).into(iconWeather);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listWeathers.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dayOfWeek;
        private TextView dateAndMonth;
        private ImageView image;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.temperature_CV);
            dateAndMonth = itemView.findViewById(R.id.dateAndMonth);
            image = itemView.findViewById(R.id.iconWeather);
            description = itemView.findViewById(R.id.description_card_5day);
        }
    }
}
