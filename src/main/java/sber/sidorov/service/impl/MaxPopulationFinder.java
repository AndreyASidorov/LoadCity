package sber.sidorov.service.impl;

import sber.sidorov.Model.City;
import sber.sidorov.service.CityListManipulator;

import java.util.Comparator;

public class MaxPopulationFinder implements CityListManipulator {


    private static MaxPopulationFinder instance;

    public static MaxPopulationFinder getInstance() {
        if (instance == null)
            instance = new MaxPopulationFinder();
        return instance;
    }

    private MaxPopulationFinder() {
    }

    public City getMaxPopulation() {
        return cities
                .stream()
                .max(Comparator.comparing(City::getPopulation))
                .orElse(null);
    }

}
