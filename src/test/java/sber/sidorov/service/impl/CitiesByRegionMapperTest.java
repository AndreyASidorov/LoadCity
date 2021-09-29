package sber.sidorov.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sber.sidorov.Model.City;
import sber.sidorov.dao.CityDao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CitiesByRegionMapperTest {
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
    void MaxPopulationFinder() {

        try (MockedStatic<CityDao> dbMock = Mockito.mockStatic(CityDao.class)) {
            dbMock.when(CityDao::getCitiesList).thenReturn(cities);

            CitiesByRegionMapper mapper = CitiesByRegionMapper.getInstance();
            Map<String, Long> actual = mapper.printCitiesAndRegions();
            Map<String, Long> expected = Map.of("Москва", (long)1, "Самарская область", (long)1, "Нижегородская область", (long)1);

            Assertions.assertEquals(expected, actual);
        }

    }


}
