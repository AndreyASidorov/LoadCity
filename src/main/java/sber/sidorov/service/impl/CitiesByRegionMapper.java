package sber.sidorov.service.impl;

import sber.sidorov.Model.City;
import sber.sidorov.dao.DBController;
import sber.sidorov.service.CityListManipulator;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CitiesByRegionMapper implements CityListManipulator {
    private static CitiesByRegionMapper instance;

    public static CitiesByRegionMapper getInstance() {
        if (instance == null)
            instance = new CitiesByRegionMapper();
        return instance;
    }

    private CitiesByRegionMapper() {
    }

    public Map<String, Long> printCitiesAndRegions() {
        return cities
                .stream()
                .collect(Collectors.groupingBy(City::getRegion, Collectors.counting()));
    }
}
