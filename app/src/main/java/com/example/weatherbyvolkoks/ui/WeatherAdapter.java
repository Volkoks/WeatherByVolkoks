package com.example.weatherbyvolkoks.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.Soc.Soc;
import com.example.weatherbyvolkoks.data.Soc.SocialDataSource;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private SocialDataSource dataSource;

    public WeatherAdapter(SocialDataSource dataSource) {
        this.dataSource = dataSource;
    }


    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        Soc soc = dataSource.getSoc(position);
        holder.setData(soc.getDayOfWeek(),soc.getDateAndMonth(),soc.getIconWeather());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
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
      public void setData(String dayOdWeek, String dateAndMonth, int iconWeather){
            this.dayOfWeek.setText(dayOdWeek);
            this.dateAndMonth.setText(dateAndMonth);
            image.setImageResource(iconWeather);
      }
    }
}
