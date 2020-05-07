package com.example.weatherbyvolkoks.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.weatherbyvolkoks.Constants;
import com.example.weatherbyvolkoks.Parcel;
import com.example.weatherbyvolkoks.R;

public class CitySelectionScreen extends AppCompatActivity implements Constants {

    private EditText enterCitySelection;
    private Button btnChooseCityAndTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_selection_screen);



        btnChooseCityAndTemperature = findViewById(R.id.button_choose_a_city_and_temperature);
        enterCitySelection = findViewById(R.id.enter_city_selection);
        clickToBtnBack();
        clickToBtnChooseCityAndTemperature();

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
