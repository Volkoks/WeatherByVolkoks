package com.example.weatherbyvolkoks.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherbyvolkoks.R;

import java.util.List;


public class CityHistoryAdapter extends RecyclerView.Adapter<CityHistoryAdapter.ViewHolder> {

    private List<String> citys;

    public CityHistoryAdapter(List<String> citys) {
        this.citys = citys;
    }

    @NonNull
    @Override
    public CityHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityHistoryAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull CityHistoryAdapter.ViewHolder holder, int position) {
        holder.cityAdd.setText(citys.get(position));
    }

    @Override
    public int getItemCount() {
        return citys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cityAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityAdd = itemView.findViewById(R.id.textView_city_add);
        }

    }
}
