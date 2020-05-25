package com.example.weatherbyvolkoks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataStorageSingletone implements Serializable {

    private static DataStorageSingletone instance = null;
    private static final Object synchObj = new Object();


    private List<String> citys;

    private DataStorageSingletone() {
        citys = new ArrayList<>();
    }

    public static DataStorageSingletone getInstance() {
        if (instance == null) {
            synchronized (synchObj) {
                if (instance == null) {
                    instance = new DataStorageSingletone();
                }
            }
        }
        return instance;
    }

    public List<String> getCitys() {
        synchronized (synchObj) {
            return citys;
        }
    }

    public void setCitys(List<String> citys) {
        synchronized (synchObj) {
            this.citys = citys;
        }
    }
    public void addCitiesList(String city) {
        synchronized (synchObj) {
            citys.add(city);
        }
    }

    public void clearCitiesList() {
        synchronized (synchObj) {
            citys.clear();
        }
    }
}
