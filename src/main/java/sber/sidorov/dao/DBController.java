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

import static sber.sidorov.config.PropertyManager.*;

public class DBController {

    public static Connection connect;

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
