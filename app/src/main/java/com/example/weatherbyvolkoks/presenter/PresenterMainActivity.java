package com.example.weatherbyvolkoks.presenter;

import android.widget.ImageView;

import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.WeatherAPI_5Day.WeatherRequest5Day;
import com.example.weatherbyvolkoks.loaderModel.ILoadModel;
import com.example.weatherbyvolkoks.loaderModel.LoaderModel;
import com.squareup.picasso.Picasso;

public class PresenterMainActivity implements ILoadModel.ForPresenter, IPresentMainAct.ForPresenter{
    private String base_city;
    private LoaderModel model;
    private IPresentMainAct.ForView forView;

    public PresenterMainActivity(String city, IPresentMainAct.ForView forView) {
        this.base_city = city;
        this.forView = forView;
        this.model = new LoaderModel(base_city, this);
    }

    @Override
    public void loadWeather(WeatherRequest5Day request5Day) {
        forView.setListView(request5Day);
    }

    @Override
    public void weatherImageInit(WeatherRequest5Day request5Day, ImageView iconWeather) {
        String main = request5Day.getListWeathers()[0].getWeather()[0].getMain();
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
    public void updateWeather() {
        model.initLoadWeather(base_city);
    }
}
