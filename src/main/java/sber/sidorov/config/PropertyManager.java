package sber.sidorov.config;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private static final String APP_PROPS_PATH = "src/main/resources/app.properties";
    public static String dbInitFile;
    public static String dbUrl;
    public static String user;
    public static String password;
    public static String dbDriver;

    /**
     * Инициализация свойств
     */
    static {
        try {
            Properties property = new Properties();
            property.load(new FileInputStream(APP_PROPS_PATH));
            dbInitFile = property.getProperty("filename");
            dbUrl = property.getProperty("DBUrl");
            user = property.getProperty("User");
            password = property.getProperty("Password");
            dbDriver = property.getProperty("DBDriver");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
