package sber.sidorov.utils;

import sber.sidorov.Model.City;

public class ParseString {
    public static City parseLineToCity(String line) {
        String[] cityColumns = line.split(";");
        return new City(
                Integer.parseInt(cityColumns[0]),
                cityColumns[1],
                cityColumns[2],
                cityColumns[3],
                Integer.parseInt(cityColumns[4]),
                Integer.parseInt(cityColumns[5]));
    }
}
