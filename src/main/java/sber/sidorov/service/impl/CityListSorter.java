package sber.sidorov.service.impl;

import sber.sidorov.Model.City;
import sber.sidorov.service.CityListManipulator;

import java.util.*;

public class CityListSorter implements CityListManipulator {

    private static CityListSorter instance;

    public static CityListSorter getInstance() {
        if (instance == null)
            instance = new CityListSorter();
        return instance;
    }

    /**
     * сортировка на алфавиту в обратном порядке
     * @return сортированный список в обратном порядке
     */
    public List<City> nameSorting() {
        List<City> sortedCitiesList = new ArrayList<>(cities);
        Comparator<City> comparator = Comparator.comparing(city -> city.getName().toLowerCase(Locale.ROOT));
        sortedCitiesList.sort(comparator.reversed());
        return sortedCitiesList;
    }

    /**
     * сортировка на алфавиту в обратном порядке и по округу
     * @return сортированный список в обратном порядке и по округу
     */
    public List<City> districtAndNameSorting() {
        List<City> sortedCitiesList = new ArrayList<>(cities);
        Comparator<City> namesAndRegionsComparator = Comparator.comparing(City::getDistrict).thenComparing(City::getName);
        sortedCitiesList.sort(namesAndRegionsComparator.thenComparing(namesAndRegionsComparator).reversed());
        return sortedCitiesList;
    }
}
