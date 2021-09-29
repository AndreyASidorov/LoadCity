package sber.sidorov;

import sber.sidorov.Model.City;
import sber.sidorov.dao.CityDao;
import sber.sidorov.service.impl.*;
import sber.sidorov.utils.ListPrinter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static sber.sidorov.config.PropertyManager.*;
import static sber.sidorov.dao.CityDao.*;
import static sber.sidorov.dao.DBController.*;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        createConnection();
        dbCreate();
        dbInit(dbInitFile);

        Scanner scanner = new Scanner(System.in);

        String answer;
        boolean exit = false;

        while (!exit) {
            System.out.println(
                    "\nВыберете требуемое действие:\n" +
                            "1) Список городов.\n" +
                            "2) Отсортированный список городов в алфавитном порядке.\n" +
                            "3) Отсортированный список городов в алфавитном порядке и ФО.\n" +
                            "4) Город с наибольшим населением.\n" +
                            "5) Количество городов по регионам.\n" +
                            "6) Выход.\n"
            );
            if (scanner.hasNext()) {
                answer = scanner.next();
                switch (answer) {
                    case "1":
                        GetCityList printer = GetCityList.getInstance();
                        ListPrinter.printCitiesList(printer.getCityList());
                        break;
                    case "2":
                        CityListSorter sorter = CityListSorter.getInstance();
                        List<City> sortedByName = sorter.nameSorting();
                        ListPrinter.printCitiesList(sortedByName);
                        break;
                    case "3":
                        sorter = CityListSorter.getInstance();
                        List<City> sortedByNameAndRegion = sorter.districtAndNameSorting();
                        ListPrinter.printCitiesList(sortedByNameAndRegion);
                        break;
                    case "4":
                        MaxPopulationFinder finder = MaxPopulationFinder.getInstance();
                        City city = finder.getMaxPopulation();
                        System.out.println("[" + city.getId() + "]" + " = " + city.getPopulation());
                        break;
                    case "5":
                        CitiesByRegionMapper mapper = CitiesByRegionMapper.getInstance();
                        mapper.printCitiesAndRegions()
                                .forEach((key, value) -> System.out.println(key + " - " + value));
                        break;
                    case "6":
                        scanner.close();
                        closeConnection();
                        exit = true;
                        break;
                }
            }
        }
    }
}


