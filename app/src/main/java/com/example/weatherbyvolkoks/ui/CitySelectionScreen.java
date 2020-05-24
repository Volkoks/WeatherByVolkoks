package com.example.weatherbyvolkoks.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.weatherbyvolkoks.BaseActivity;
import com.example.weatherbyvolkoks.CityHistoryAdapter;
import com.example.weatherbyvolkoks.data.Soc.SocialDataSource;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherbyvolkoks.data.Constants;
import com.example.weatherbyvolkoks.data.Parcel;
import com.example.weatherbyvolkoks.R;

import java.util.ArrayList;

import java.util.List;
import java.util.regex.Pattern;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class CitySelectionScreen extends BaseActivity implements Constants {
    private MaterialButton addCity;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private TextInputLayout textInputLayout;
    private TextInputEditText enterCitySelection;
    private MaterialButton btnChooseCityAndTemperature;
    private Pattern checkCity = Pattern.compile("^[A-Z][a-z]{1,}$");
    private Pattern checkCityRu = Pattern.compile("^[А-ЯЁ][а-яё]{1,}$");

    private List<String> citys = new ArrayList<>();

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
            citys.add(enterCitySelection.getText().toString());
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(CitySelectionScreen.this, VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            CityHistoryAdapter cityHistoryAdapter = new CityHistoryAdapter(citys);
            recyclerView.setAdapter(cityHistoryAdapter);
        }
    };

}
