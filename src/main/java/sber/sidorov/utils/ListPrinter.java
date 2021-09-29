package sber.sidorov.utils;

import sber.sidorov.Model.City;

import java.util.List;

/**
 * печать в консоль листа с городами
 */
public class ListPrinter {
    /**
     * печать в консоль листа с городами
     * @param cities cписок городов для печати
     */
    public static void printCitiesList(List<City> cities) {
        for (City city : cities) {
            System.out.println(city);
        }
    }
}
