package com.example.weatherbyvolkoks.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.EducationSource;
import com.example.weatherbyvolkoks.data.HistoryCity;

import java.util.List;


public class CityHistoryAdapter extends RecyclerView.Adapter<CityHistoryAdapter.ViewHolder> {

    private EducationSource database;
    private long menuPosition;

    public CityHistoryAdapter(EducationSource database) {
        this.database = database;
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
        List<HistoryCity> historyCities = database.getHistoryCities();
        HistoryCity historyCity = historyCities.get(position);
        holder.cityAdd.setText(historyCity.cityName);
        holder.temp.setText(String.format(String.valueOf(historyCity.temperature)));
        holder.descrpt.setText(historyCity.description);

    }

    @Override
    public int getItemCount() {
        return (int) database.getCountCity();
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
