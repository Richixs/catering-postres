package cateringpostres.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase responsable de establecer la conexión con la base de datos
 * utilizando un archivo de propiedades.
 */
public class DatabaseConnection {
    private static final String PROPERTIES_FILE = "db.properties";
    private static Connection connection;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/" + PROPERTIES_FILE));

            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
