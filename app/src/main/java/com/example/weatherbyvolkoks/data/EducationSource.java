package com.example.weatherbyvolkoks.data;

import java.util.List;

public class EducationSource {
    private EducationDao educationDao;
    private List<HistoryCity> historyCities;

    public EducationSource(EducationDao educationDao) {
        this.educationDao = educationDao;
    }

    public void loadHistoryCity() {
        historyCities = educationDao.getAllCity();
    }

    public List<HistoryCity> getHistoryCities() {
        if (historyCities == null) {
            loadHistoryCity();
        }
        return historyCities;
    }

    public void addCity(HistoryCity historyCity) {
        educationDao.addCity(historyCity);
        loadHistoryCity();
    }

    public void deleteCity(HistoryCity historyCity) {
        educationDao.deleteCity(historyCity);
        loadHistoryCity();
    }

    public long getCountCity() {
        return educationDao.getCountHistoryCity();
    }

    public void getCityByName(String cityName) {
        educationDao.getCityByName(cityName);
        loadHistoryCity();
    }
}
