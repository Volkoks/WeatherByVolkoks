package com.example.weatherbyvolkoks.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.weatherbyvolkoks.BaseActivity;
import com.example.weatherbyvolkoks.MyApp;
import com.example.weatherbyvolkoks.data.API.WeatherRequest;
import com.example.weatherbyvolkoks.data.EducationDao;
import com.example.weatherbyvolkoks.data.EducationSource;
import com.example.weatherbyvolkoks.data.loaderWeather.ILoaderWeather;
import com.example.weatherbyvolkoks.data.loaderWeather.LoaderWeather;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherbyvolkoks.data.Constants;
import com.example.weatherbyvolkoks.data.Parcel;
import com.example.weatherbyvolkoks.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Response;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class CitySelectionScreen extends BaseActivity implements Constants, ILoaderWeather{

    private static String citys = null;
    private MaterialButton addCity;
    private RecyclerView recyclerView;
    private TextInputLayout textInputLayout;
    private TextInputEditText enterCitySelection;
    private MaterialButton btnChooseCityAndTemperature;
    private EducationSource educationSource;

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

        enterCitySelection.setOnKeyListener(selectCityListenerMK);
        addCity.setOnClickListener(addCityToRecyclerView);

        initRecyclerView();

    }
    private void initWeatherToAPI() {
        LoaderWeather loaderWeather = new LoaderWeather(this);
        loaderWeather.downloadWeather(citys);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(CitySelectionScreen.this, VERTICAL, false);
        EducationDao educationDao = MyApp.getEducationDB().getEducationDao();
        educationSource = new EducationSource(educationDao);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(CitySelectionScreen.this, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void init() {
        textInputLayout = findViewById(R.id.textInputCitySelection);
        btnChooseCityAndTemperature = findViewById(R.id.button_choose_a_city_and_temperature);
        enterCitySelection = findViewById(R.id.enter_city_selection);
        addCity = findViewById(R.id.btn_add_city);
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
    private View.OnClickListener addCityToRecyclerView = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            citys = enterCitySelection.getText().toString();
            initWeatherToAPI();
            CityHistoryAdapter cityHistoryAdapter = new CityHistoryAdapter(educationSource);
            recyclerView.setAdapter(cityHistoryAdapter);
        }
    };

    @Override
    public void activate(Response<WeatherRequest> response) {

    }

    @Override
    public void ADError(String title, String error) {

    }
}
