package sber.sidorov.dao;

import java.sql.*;

import static sber.sidorov.config.PropertyManager.*;

public class DBController {

    public static Connection connect;

    /**
     * подключение к БД
     * @return результат подключения
     */
    public static int createConnection() {
        try {
            Class.forName(dbDriver);
            connect = DriverManager.getConnection(dbUrl, user, password);
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
            return 0;
        }
    }

    /**
     * закрывает подключение к базе
     * @return результат подключения
     */
    public static int closeConnection() {
        try {
            connect.close();
            return 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}
