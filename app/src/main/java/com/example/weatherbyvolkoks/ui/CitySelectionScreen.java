package com.example.weatherbyvolkoks.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.weatherbyvolkoks.BaseActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.weatherbyvolkoks.data.Constants;
import com.example.weatherbyvolkoks.data.Parcel;
import com.example.weatherbyvolkoks.R;

import java.util.regex.Pattern;

public class CitySelectionScreen extends BaseActivity implements Constants {

    private TextInputLayout textInputLayout;
    private TextInputEditText enterCitySelection;
    private Button btnChooseCityAndTemperature;
    private Pattern checkCity = Pattern.compile("^[A-Z][a-z]{1,}$");
    private Pattern checkCityRu = Pattern.compile("^[А-ЯЁ][а-яё]{1,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_selection_screen);

        textInputLayout = findViewById(R.id.textInputCitySelection);
        btnChooseCityAndTemperature = findViewById(R.id.button_choose_a_city_and_temperature);
        enterCitySelection = findViewById(R.id.enter_city_selection);
        validateCity();
        clickToBtnBack();
        clickToBtnChooseCity();

        enterCitySelection.setOnKeyListener(selectCityListenerMK);

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

}
