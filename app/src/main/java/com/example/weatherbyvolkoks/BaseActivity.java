package com.example.weatherbyvolkoks;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
   private static final String NSP = "LOGIN";
   private static final String isDarkThemes = "IS_DARK_THEME";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isDarkTheme()){
            setTheme(R.style.AppDarkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }
    }
    protected boolean isDarkTheme(){
        SharedPreferences sP = getSharedPreferences(NSP, MODE_PRIVATE);
        return sP.getBoolean(isDarkThemes, true);
    }
    protected void setIsDarkTheme(boolean isDarkTheme){
        SharedPreferences sP = getSharedPreferences(NSP, MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();
        editor.putBoolean(isDarkThemes, isDarkTheme);
        editor.apply();
    }
}
