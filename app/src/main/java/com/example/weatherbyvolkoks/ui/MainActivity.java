package com.example.weatherbyvolkoks.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.weatherbyvolkoks.BaseActivity;
import com.example.weatherbyvolkoks.Parcel;
import com.example.weatherbyvolkoks.R;
import com.example.weatherbyvolkoks.data.SocSourceBuilder;
import com.example.weatherbyvolkoks.data.SocialDataSource;

public class MainActivity extends BaseActivity {
    private TextView clickingOnCityView;

    private final static int REQUEST_CODE = 1;
    private final static int SETTING_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SocialDataSource sourceData = new SocSourceBuilder().setResources(getResources()).build();

        clickingOnCityView = findViewById(R.id.City);

        initRecyclerView(sourceData);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), ScreenSetting.class);
                startActivityForResult(intent, SETTING_CODE);
                break;
            case R.id.enter_city_selection2:
                Intent intent2 = new Intent(getApplicationContext(), CitySelectionScreen.class);
                startActivityForResult(intent2, REQUEST_CODE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView(SocialDataSource data) {

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        WeatherAdapter adapter = new WeatherAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                Parcel parcel = (Parcel) data.getSerializableExtra("parcel");
                clickingOnCityView.setText(parcel.cityName);
                break;
            case SETTING_CODE:
                recreate();
                break;
        }

    }
}
