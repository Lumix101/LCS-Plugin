package de.lumix.lumcraftserver.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.*;

public class MySQL {
    private String HOST, PORT, DB, USER, PASSWORD;
    private Connection conn;

    public MySQL(String host,String port,String db,String user,String passsword) {
        HOST = host;
        PORT = port;
        DB = db;
        USER = user;
        PASSWORD = passsword;

        connect();
    }

    private void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://"+ HOST + ":" + PORT + "/" + DB + "?autoReconnect=true", USER, PASSWORD);

            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] Verbindung zur Datenbank wurde aufgebaut!");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            if (conn != null) {
                conn.close();
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[LCS-MainPlugin] Verbindung zur Datenbank wurde geschlossen!");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String query) {
        Statement state;
        try {
            state = conn.createStatement();
            state.executeUpdate(query);
            state.close();
        } catch(SQLException e) {
            connect();
            e.printStackTrace();
        }
    }

    public ResultSet query(String query) {
        ResultSet res = null;

        try {
            Statement state = conn.createStatement();
            res = state.executeQuery(query);
        } catch(SQLException e) {
            connect();
            e.printStackTrace();
        }
        return res;
    }

}
