package sber.sidorov.dao;

import sber.sidorov.Model.City;
import sber.sidorov.utils.ParseString;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static sber.sidorov.dao.DBController.*;

public class CityDao {
    public static ResultSet rs;

    /**
     * Создание базы данных
     * @return результат создания
     */
    public static int dbCreate() {
        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE if not exists CITIES" +
                            "(ID INT," +
                            "NAME VARCHAR(50) not null," +
                            "REGION VARCHAR(50)," +
                            "DISTRICT VARCHAR(50)," +
                            "POPULATION INT," +
                            "FOUNDATION INT," +
                            "primary key (ID)); ");
            return 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    /**
     * Инициализация базы данных
     * @param fileName путь к списку городов
     * @throws IOException
     * @throws SQLException
     */
    public static void dbInit(String fileName) throws IOException, SQLException {
        PreparedStatement statement = connect.prepareStatement("INSERT INTO CITIES (ID, NAME, REGION, DISTRICT, POPULATION, FOUNDATION) VALUES (?, ?, ?, ?, ?, ?);");
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        while(scanner.hasNext()){
            City city = ParseString.parseLineToCity(scanner.nextLine());
            if (!checkIfExists(city)) {
                statement.setInt(1, city.getId());
                statement.setString(2, city.getName());
                statement.setString(3, city.getRegion());
                statement.setString(4, city.getDistrict());
                statement.setInt(5, city.getPopulation());
                statement.setInt(6, city.getFoundation());
                statement.addBatch();
            }
        }
        statement.executeBatch();
        scanner.close();
    }

    /**
     * Проверка на дубль в базе
     * @param city город для проверки
     * @return результат проверки на дубль
     * @throws SQLException
     */
    public static boolean checkIfExists (City city) throws SQLException {
        PreparedStatement statement = connect.prepareStatement("SELECT * FROM CITIES WHERE ID = ?");
        statement.setInt(1, city.getId());
        rs = statement.executeQuery();

        return rs.next();
    }

    /**
     * Получение городов из базы
     * @return список всех городов
     */
    public static List<City> getCitiesList() {
        try {
            List<City> cities = new ArrayList<>();
            Statement statement = connect.createStatement();
            rs = statement.executeQuery("SELECT * FROM CITIES");
            while (rs.next()) {
                cities.add(
                        new City(
                                rs.getInt("ID"),
                                rs.getString("NAME"),
                                rs.getString("REGION"),
                                rs.getString("DISTRICT"),
                                rs.getInt("POPULATION"),
                                rs.getInt("FOUNDATION")));
            }
            return cities;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
