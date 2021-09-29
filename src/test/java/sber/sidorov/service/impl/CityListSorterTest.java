package sber.sidorov.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sber.sidorov.Model.City;
import sber.sidorov.dao.CityDao;
import sber.sidorov.dao.DBController;

import java.util.Arrays;
import java.util.List;

class CityListSorterTest {

    private static List<City> cities;
    private static City city1;
    private static City city2;
    private static City city3;


    @BeforeAll
    static void setUp() {
        city1 = new City(1, "Москва", "Москва", "Центральный", 12655050, 1147);
        city2 = new City(2, "Самара", "Самарская область", "Приволжский", 1144759, 1586);
        city3 = new City(3, "Нижний Новгород", "Нижегородская область", "Приволжский", 1244254, 1221);

        cities = Arrays.asList(city1, city2, city3);
    }

    @Test
    void nameSorting() {
        try (MockedStatic<CityDao> dbMock = Mockito.mockStatic(CityDao.class)) {
            dbMock.when(CityDao::getCitiesList).thenReturn(cities);

            CityListSorter sorter = CityListSorter.getInstance();

            List<City> expected = Arrays.asList(city2, city3, city1);
            List<City> actual = sorter.nameSorting();

            Assertions.assertEquals(expected, actual);
        }
    }

    @Test
    void districtAndNameSorting() {
        try (MockedStatic<CityDao> dbMock = Mockito.mockStatic(CityDao.class)) {
            dbMock.when(CityDao::getCitiesList).thenReturn(cities);

            CityListSorter sorter = CityListSorter.getInstance();

            List<City> expected = Arrays.asList(city1, city2, city3);
            List<City> actual = sorter.districtAndNameSorting();

            Assertions.assertEquals(expected, actual);
        }
    }
}