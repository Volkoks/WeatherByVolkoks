package com.example.weatherbyvolkoks.CitySelectionScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.weatherbyvolkoks.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CitySelectionFragment extends Fragment {
 private ImageButton btnBack;
    private EditText enterCitySelection;
    private EditText enterTemperatureSelection;
    private Button btnChooseCityAndTemperature;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_city_selection, container, false);

        btnBack = rootView.findViewById(R.id.button_back);
        btnChooseCityAndTemperature = rootView.findViewById(R.id.button_choose_a_city_and_temperature);
        enterCitySelection = rootView.findViewById(R.id.enter_city_selection);
        enterTemperatureSelection = rootView.findViewById(R.id.enter_temperature_selection);
        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clickBtnBack();
        clickBtnChooseCityAndTemperature();
    }

    private void clickBtnBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });
    }
    private void closeFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
    private void clickBtnChooseCityAndTemperature(){
        btnChooseCityAndTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)getActivity().findViewById(R.id.City)).setText(enterCitySelection.getText().toString());
                ((TextView)getActivity().findViewById(R.id.Temperature)).setText(enterTemperatureSelection.getText().toString());
            }
        });
    }
}
