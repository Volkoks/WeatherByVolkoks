package com.example.weatherbyvolkoks.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.weatherbyvolkoks.Constants;
import com.example.weatherbyvolkoks.Parcel;
import com.example.weatherbyvolkoks.R;

import java.util.regex.Pattern;

public class CitySelectionScreen extends AppCompatActivity implements Constants {

    private TextInputLayout textInputLayout;
    private TextInputEditText enterCitySelection;
    private Button btnChooseCityAndTemperature;
    private Pattern checkCity = Pattern.compile("^[A-Z][a-z]{1,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_selection_screen);


        textInputLayout = findViewById(R.id.textInputLayout);
        btnChooseCityAndTemperature = findViewById(R.id.button_choose_a_city_and_temperature);
        enterCitySelection = findViewById(R.id.enter_city_selection);
        validateCity();
        clickToBtnBack();
        clickToBtnChooseCityAndTemperature();




    }

    private void validateCity() {
        enterCitySelection.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) return;
                TextView tv = (TextView) v;
                String value = tv.getText().toString();
                if (checkCity.matcher(value).matches()){
                    showError(textInputLayout, null);
                }else {
                    showError(textInputLayout,"Город должен начинаться с заглавной буквы и быть без цифр!");
                }
            }
        });
    }

    private void showError(TextInputLayout textInputLayout, String message) {
        textInputLayout.setError(message);
    }


    private Parcel createParcel(){
        Parcel parcel = new Parcel();
        parcel.cityName = enterCitySelection.getText().toString();
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
    public void clickToBtnChooseCityAndTemperature(){
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

}
