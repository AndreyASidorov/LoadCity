package sber.sidorov.service.impl;

import sber.sidorov.Model.City;
import sber.sidorov.service.CityListManipulator;

import java.util.List;

public class GetCityList implements CityListManipulator {
    private static GetCityList instance;

    public static GetCityList getInstance() {
        if (instance == null)
            instance = new GetCityList();
        return instance;
    }

    private GetCityList() {}

    public List<City> getCityList() {
        return cities;
    }
}
