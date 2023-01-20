package de.lumix.lumcraftserver.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {

    private final String host = "localhost";
    private final String port = "3306";
    private final String db = "lc-craft";
    private final String user = "root";
    private final String password = "";

    private Connection connection;

    public boolean isConnected() {
        return (connection != null);
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if (!isConnected()) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" +
                            host + ":" + port + "/" + db + "?useSSL=false",
                    user, password);
        }
    }

    public void discconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return  connection;
    }
}
