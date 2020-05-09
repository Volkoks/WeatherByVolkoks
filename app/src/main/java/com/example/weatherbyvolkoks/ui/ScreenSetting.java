package com.example.weatherbyvolkoks.ui;

import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import com.example.weatherbyvolkoks.BaseActivity;
import com.example.weatherbyvolkoks.R;

public class ScreenSetting extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_setting);

        clickToBtnBack();

        SwitchCompat switchNigthMode = findViewById(R.id.switch_night_mode);
        switchNigthMode.setChecked(isDarkTheme());
        switchNigthMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setIsDarkTheme(isChecked);
                recreate();
            }
        });
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
}
