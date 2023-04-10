package com.example.vote_sustem.console;

import com.example.vote_sustem.console.DB;

import java.sql.*;
import java.util.Scanner;

public class User {
    static void getCanditates(){
        try {
            Connection connection = DB.main();
            String sql = "SELECT id, name, surname FROM candidates_vote";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static void Choice()  {
        Scanner sc = new Scanner(System.in);
        Connection connection = DB.main();
        String[] s;
        try {
            System.out.println("Ведите ваше имя");
            s = sc.nextLine().split(" ");
            String sql = "SELECT name, surname, status, ihn FROM user_vote";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                if (rs.getString(1).equals(s[0]) && rs.getString(2).equals(s[1])){
                    String inn = sc.next();
                    if (rs.getString(3).equals("Active") && inn.equals(rs.getString(4))){
                        try {
                            int n, c = 0;
                            System.out.println("Введите номер кандитата");
                            n = sc.nextInt();
                            String sql1 = "SELECT id, count FROM candidates_vote";
                            Statement statement1 = connection.createStatement();
                            ResultSet rs1 = statement1.executeQuery(sql1);
                            while (rs1.next()) {
                                if (rs1.getInt(1) == n){
                                    c = rs1.getInt(2);
                                    c++;
                                    break;
                                }
                            }
                            statement1.close();
                            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE candidates_vote SET count = ? WHERE id = ?");
                            preparedStatement.setInt(1, c);
                            preparedStatement.setInt(2, n);
                            preparedStatement.executeUpdate();
                            preparedStatement = connection.prepareStatement("UPDATE user_vote SET status = 'Disable' WHERE ihn = ?");
                            preparedStatement.setString(1, inn);
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                        } catch (Exception e){
                            System.out.println("hjkhkhkhjk");
                        }
                    } else {
                        System.out.println("Ты уже голосовал");
                    }
                }
            }
        } catch (Exception e){
            System.out.println("khjkhj");
        }
    }
}
