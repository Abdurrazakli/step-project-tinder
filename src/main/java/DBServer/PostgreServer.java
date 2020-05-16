package DBServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreServer {
    public Connection createConnection(String url, String name, String password) {
        try{
            return DriverManager.getConnection(url,name,password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't connect to DB!");
        }
    }
}
