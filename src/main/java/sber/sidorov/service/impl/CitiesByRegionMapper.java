package sber.sidorov.service.impl;

import sber.sidorov.Model.City;
import sber.sidorov.service.CityListManipulator;

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

    /**
     * Количество городов по регионам
     * @return карта регион - количество
     */
    public Map<String, Long> printCitiesAndRegions() {
        return cities
                .stream()
                .collect(Collectors.groupingBy(City::getRegion, Collectors.counting()));
    }
}
