package com.example.weatherbyvolkoks.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.weatherbyvolkoks.BaseActivity;
import com.example.weatherbyvolkoks.MyApp;
import com.example.weatherbyvolkoks.data.WeatherAPI_1day.WeatherRequest;
import com.example.weatherbyvolkoks.data.dataRoom.WeatherDao;
import com.example.weatherbyvolkoks.data.dataRoom.WeatherSource;
import com.example.weatherbyvolkoks.data.loaderWeather.loaderWeather1day.ILoaderWeather;
import com.example.weatherbyvolkoks.data.loaderWeather.loaderWeather1day.LoaderWeather;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherbyvolkoks.data.Constants;
import com.example.weatherbyvolkoks.data.Parcel;
import com.example.weatherbyvolkoks.R;


import java.util.regex.Pattern;

import retrofit2.Response;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class CitySelectionScreen extends BaseActivity implements Constants, ILoaderWeather {

    private String CITY = "Moscow";
    private CityHistoryAdapter cityHistoryAdapter;
    private RecyclerView recyclerView;
    private TextInputLayout textInputLayout;
    private TextInputEditText enterCitySelection;
    private MaterialButton btnChooseCityAndTemperature;
    private WeatherSource weatherSource;

    private Pattern checkCity = Pattern.compile("^[A-Z][a-z]{1,}$");
    private Pattern checkCityRu = Pattern.compile("^[А-ЯЁ][а-яё]{1,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_selection_screen);

        init();
        validateCity();
        clickToBtnBack();
        clickToBtnChooseCity();
        initWeatherToAPI();
        enterCitySelection.setOnKeyListener(selectCityListenerMK);

        initRecyclerView();
    }

    private void initWeatherToAPI() {
        LoaderWeather loaderWeather = new LoaderWeather(this);
        loaderWeather.downloadWeather(CITY);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(CitySelectionScreen.this, VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        WeatherDao educationDao = MyApp.getEducationDB().getEducationDao();
        weatherSource = new WeatherSource(educationDao);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(CitySelectionScreen.this, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.separator));

        recyclerView.addItemDecoration(itemDecoration);
        cityHistoryAdapter = new CityHistoryAdapter(weatherSource, this);

        recyclerView.setAdapter(cityHistoryAdapter);
    }

    private void init() {
        textInputLayout = findViewById(R.id.textInputCitySelection);
        btnChooseCityAndTemperature = findViewById(R.id.button_choose_a_city_and_temperature);
        enterCitySelection = findViewById(R.id.enter_city_selection);
        recyclerView = findViewById(R.id.recyclerView_city_selection);
    }

    private void validateCity() {

        enterCitySelection.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                TextView tv = (TextView) v;
                String value = tv.getText().toString();
                if (checkCity.matcher(value).matches() || checkCityRu.matcher(value).matches()) {
                    showError(textInputLayout, null);
                } else {
                    showError(textInputLayout, "Город должен начинаться с заглавной буквы и быть без цифр!");
                }
            }
        });
    }

    private void showError(TextInputLayout textInputLayout, String message) {
        textInputLayout.setError(message);
    }


    private Parcel createParcel() {
        Parcel parcel = new Parcel();
        parcel.cityName = enterCitySelection.getText().toString();
        parcel.weatherCityName = enterCitySelection.getText().toString();
        return parcel;
    }

    public void clickToBtnBack() {
        ImageButton btnBack = findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void clickToBtnChooseCity() {

        btnChooseCityAndTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("parcel", createParcel());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    private View.OnKeyListener selectCityListenerMK = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            boolean result = false;
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                enterCitySelection.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                result = true;
            }
            return result;
        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_setting_activity, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.remove_context:

        }
        return super.onContextItemSelected(item);

    }

    @Override
    public void activate(Response<WeatherRequest> response) {

    }

    @Override
    public void ADError(String title, String error) {

    }
}
