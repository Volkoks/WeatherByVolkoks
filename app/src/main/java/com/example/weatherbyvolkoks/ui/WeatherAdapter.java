package com.example.weatherbyvolkoks.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.WeatherAPI.WeatherRequest;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private WeatherRequest weatherRequest;

    public WeatherAdapter(WeatherRequest weatherRequest) {
        this.weatherRequest = weatherRequest;
    }


    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {

    holder.dateAndMonth.setText((int) weatherRequest.getMain().getTemp());
    holder.dayOfWeek.setText(weatherRequest.getName());

    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dayOfWeek;
        private TextView dateAndMonth;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.dayOfWeek);
            dateAndMonth = itemView.findViewById(R.id.dateAndMonth);
            image = itemView.findViewById(R.id.iconWeather);
        }
    }
}
