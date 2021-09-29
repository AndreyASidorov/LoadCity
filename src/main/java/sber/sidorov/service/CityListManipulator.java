package sber.sidorov.service;

import sber.sidorov.Model.City;
import sber.sidorov.dao.CityDao;
import sber.sidorov.dao.DBController;

import java.util.List;

public interface CityListManipulator {

    List<City> cities = CityDao.getCitiesList();
}
