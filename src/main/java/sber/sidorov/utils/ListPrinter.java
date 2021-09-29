package sber.sidorov.utils;

import sber.sidorov.Model.City;

import java.util.List;

public class ListPrinter {
    public static void printCitiesList(List<City> cities) {
        for (City city : cities) {
            System.out.println(city);
        }
    }
}
