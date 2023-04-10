package com.example.vote_sustem.console;

import java.sql.*;
import java.util.*;

public class Admin {
    public static void addCandidates() {
        Scanner sc = new Scanner(System.in);
        try {
            Connection connection = DB.main();
            String sql = "INSERT INTO candidates_vote (id, name, surname, count) VALUES(?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println(preparedStatement);
            System.out.println("Введите номер");
            int id = sc.nextInt();
            preparedStatement.setInt(1, id);
            System.out.println("Введите имя");
            String name, surname;
            name = sc.next();
            preparedStatement.setString(2, name);
            System.out.println("Ведите фамилию");
            surname = sc.next();
            preparedStatement.setString(3, surname);
            preparedStatement.setInt(4, 0);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void Candidates() {
        try {
            Connection connection = DB.main();
            String sql = "SELECT name FROM candidates_vote";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void Users() {
        try {
            Connection connection = DB.main();
            String sql = "SELECT name, surname FROM user_vote";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        try {
            Connection connection = DB.main();
            String sql = "INSERT INTO user_vote (id,name, surname, ihn, status) VALUES(?, ?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println(preparedStatement);
            System.out.println("Введите номер");
            int id = sc.nextInt();
            System.out.println("Введите имя");
            preparedStatement.setInt(1, id);
            String name, surname, inn;
            name = sc.next();
            preparedStatement.setString(2, name);
            System.out.println("Ведите фамилию");
            surname = sc.next();
            preparedStatement.setString(3, surname);
            System.out.println("Ведите INN");
            inn = sc.next();
            preparedStatement.setString(4, inn);
            preparedStatement.setString(5, "Active");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void Result() {
        try {
            Connection connection = DB.main();
            String sql = "SELECT name, surname, count FROM candidates_vote";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            while (rs.next()) {
                map.put(rs.getString(1) + " " + rs.getString(2), rs.getInt(3));
            }
            List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
            // Определение компаратора для сравнения значений элементов Map.Entry в порядке убывания
            Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                    return e2.getValue().compareTo(e1.getValue());
                }
            };

            // Сортировка списка Map.Entry в порядке убывания значения
            Collections.sort(list, valueComparator);

            // Создание нового экземпляра LinkedHashMap для хранения отсортированных элементов
            LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

            // Перебор элементов списка Map.Entry и добавление их в LinkedHashMap
            for (Map.Entry<String, Integer> entry : list) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }

            // Вывод отсортированного по значению HashMap в порядке убывания
            System.out.println(sortedMap);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Result();
    }
}
