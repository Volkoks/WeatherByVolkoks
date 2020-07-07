package com.example.weatherbyvolkoks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MyMapsFragment extends Fragment {

    private GoogleMap myMap;
    private String city;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            myMap = googleMap;
            GetCityes getCityes = (GetCityes) getActivity();
            city = getCityes.getCity();
            searchCityOnMap(city);
            myMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                   city = getCityes.getCity();
                   searchCityOnMap(city);

                    Toast.makeText(getContext(), "Город:"+city, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void searchCityOnMap(String cities) {
        final Geocoder geocoder = new Geocoder(getContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Address> addresses = geocoder.getFromLocationName(cities, 1);
                    if (addresses.size() > 0) {
                        final LatLng location = new LatLng(addresses.get(0).getLatitude(),
                                addresses.get(0).getLongitude());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myMap.addMarker(new MarkerOptions()
                                        .position(location)
                                        .title(cities));
                                myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, (float) 7));
                            }
                        });
                    } else {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "Не нашли", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}