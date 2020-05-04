package com.example.weatherbyvolkoks.SocSourceBuilder;

import android.content.res.Resources;
import android.content.res.TypedArray;
import com.example.weatherbyvolkoks.R;

import java.util.ArrayList;
import java.util.List;

public class SocSource implements SocialDataSource {
    private List<Soc> dataSource;
    private Resources resources;

    public SocSource(Resources resources) {
        this.dataSource = new ArrayList<>(7);
        this.resources = resources;
    }
    public Soc getSoc(int position){
        return dataSource.get(position);
    }
    public int size(){
        return  dataSource.size();
    }
    public SocSource init(){
        String[] dayOfWeek = resources.getStringArray(R.array.dayOfWeek);
        String[] dateAndMonth = resources.getStringArray(R.array.dateAndMonth);
        int[] iconWeather = getImageArray();

        for (int i = 0; i < dayOfWeek.length; i++) {
            dataSource.add(new Soc(dayOfWeek[i],dateAndMonth[i],iconWeather[i]));
        }
        return this;
    }

    private int[] getImageArray() {
        TypedArray iconWeather = resources.obtainTypedArray(R.array.iconWeather);
        int length = iconWeather.length();
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = iconWeather.getResourceId(i,0);
        }
        return answer;
    }
}
