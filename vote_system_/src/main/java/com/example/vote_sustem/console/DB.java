package com.example.vote_sustem.console;


import java.sql.*;
public class DB {
    public static Connection main() {
        String url = "jdbc:mysql://localhost:3306/my_db";
        String username = "root";
        String password = "qwerty";

        try {
            return DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

}