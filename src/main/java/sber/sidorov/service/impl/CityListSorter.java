package sber.sidorov.service.impl;

import sber.sidorov.Model.City;
import sber.sidorov.dao.DBController;
import sber.sidorov.service.CityListManipulator;

import java.util.*;
import java.util.stream.Collectors;

public class CityListSorter implements CityListManipulator {

    private static CityListSorter instance;

    public static CityListSorter getInstance() {
        if (instance == null)
            instance = new CityListSorter();
        return instance;
    }

    public List<City> nameSorting() {
        List<City> sortedCitiesList = new ArrayList<>(cities);
        Comparator<City> comparator = Comparator.comparing(city -> city.getName().toLowerCase(Locale.ROOT));
        sortedCitiesList.sort(comparator.reversed());
        return sortedCitiesList;
    }

    public List<City> districtAndNameSorting() {
        List<City> sortedCitiesList = new ArrayList<>(cities);
        Comparator<City> namesAndRegionsComparator = Comparator.comparing(City::getDistrict).thenComparing(City::getName);
        sortedCitiesList.sort(namesAndRegionsComparator.thenComparing(namesAndRegionsComparator).reversed());
        return sortedCitiesList;
    }
}
