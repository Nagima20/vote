package com.example.vote_sustem.console;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static void menu(){
        System.out.println("_WELCOME_");
        System.out.println("""
                -Главное Меню-
                Админ: 1
                Пользователь: 2
                Выход: 3
                """);
    }
    static void menuAdmin(){
        System.out.println("""
                -Меню Админа-
                Добавить кандидата: 1
                Показать кандидатов: 2
                Добавить пользователя: 3
                Показать пользователей: 4
                Результат: 5
                Выход: 6
                """);
    }
    static void userMenu(){
        System.out.println("""
                        Меню
                        Показать кандидатов: 1
                        Голосовать: 2
                        Выход: 3
                        """);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            menu();
            int n;
            n = sc.nextInt();
            if (n == 1){
                String login, pass;
                System.out.println("Логин:");
                login = sc.next();
                System.out.println("Пароль:");
                pass = sc.next();
                boolean flag = false;
                try {
                    Connection connection = DB.main();
                    String sql = "SELECT login, password FROM admin_vote";
                    assert connection != null;
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);
                    while (rs.next()) {
                        if (login.equals(rs.getString(1)) && pass.equals(rs.getString(2))){
                            flag = true;
                            break;
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (flag){
                    while (true){
                        menuAdmin();
                        int n1;
                        n1 = sc.nextInt();
                        if (n1 == 1){
                            Admin.addCandidates();
                        } else if (n1 == 2){
                            Admin.Candidates();
                        } else if (n1 == 3){
                            Admin.addUser();
                        } else if (n1 == 4){
                            Admin.Users();
                        } else if (n1 == 5){
                            Admin.Result();
                        } else if (n1 == 6){
                            break;
                        } else {
                            System.out.println("Ошибка");
                        }
                    }

                } else {
                    System.out.println("Неверный аккаунт");
                }
            } else if (n == 3){
                break;
            } else if (n == 2){
                int n1;
                while (true){
                    userMenu();
                    n1 = sc.nextInt();
                    if (n1 == 1){
                        User.getCanditates();
                    } else if (n1 == 2){
                        User.Choice();
                    } else if (n1 == 3){
                        break;
                    }
                }
            }
        }
    }
}
