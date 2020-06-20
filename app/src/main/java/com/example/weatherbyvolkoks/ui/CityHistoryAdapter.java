package com.example.weatherbyvolkoks.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.dataRoom.LoaderWeatherDB;
import com.example.weatherbyvolkoks.data.dataRoom.WeatherSource;
import com.example.weatherbyvolkoks.data.dataRoom.HistoryCity;

import java.util.List;


public class CityHistoryAdapter extends RecyclerView.Adapter<CityHistoryAdapter.ViewHolder> {

    private LoaderWeatherDB db;
//    private WeatherSource database;
    private Activity activity;
    private long menuPosition;

    public CityHistoryAdapter(LoaderWeatherDB db, Activity activity) {
//        this.database = database;
        this.db = db;
        this.activity = activity;
    }


    @NonNull
    @Override
    public CityHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull CityHistoryAdapter.ViewHolder holder, int position) {
        List<HistoryCity> historyCities = db.getHistoryCities();
        HistoryCity historyCity = historyCities.get(position);
        holder.cityAdd.setText(historyCity.cityName);
        holder.temp.setText(String.format(String.valueOf(historyCity.temperature)));
        holder.descrpt.setText(historyCity.description);
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                menuPosition = position;
                return false;
            }
        });
        if (activity != null) {
            activity.registerForContextMenu(holder.cardView);
        }
    }

    @Override
    public int getItemCount() {
        return (int) db.getCountCity();
    }

    public long getMenuPosition() {
        return menuPosition;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cityAdd;
        private TextView temp;
        private TextView descrpt;
        View cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            cityAdd = itemView.findViewById(R.id.textView_city_add);
            temp = itemView.findViewById(R.id.temp_cardView);
            descrpt = itemView.findViewById(R.id.description_cardView);
        }

    }
}
