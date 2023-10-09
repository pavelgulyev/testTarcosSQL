package com.example.demo1.DAO;

import java.sql.*;

public class SQLiteDAOFabrica  extends DAOFabrica {
    static Connection connection = null;
    /**
     * Функция создания подключения к базе данных
     */
    public static Connection createConnection(){
        try {
            String url = "jdbc:sqlite:managementchanges.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS User (" +
                    "USER_ID INTEGER PRIMARY KEY," +
                    "LOGIN VARCHAR(255)," +
                    "PASSWORD VARCHAR(255)"+
                    ");");
            Statement stmt2 = connection.createStatement();
            stmt2.executeUpdate("CREATE TABLE IF NOT EXISTS Change (" +
                    "Change_ID INTEGER PRIMARY KEY," +
                    "Description VARCHAR(255)," +
                    "priority VARCHAR(255), "+
                    "status VARCHAR(255), " +
                    "Services_Id INTEGER, " +
                    "User_Id INTEGER " +
//                    "FOREIGN KEY (Services_Id)  REFERENCES Service (Service_ID), " +
//                    "FOREIGN KEY (User_Id)  REFERENCES User (Service_ID) " +
                    ");");
            /*
            Таблица "Изменения" (Changes)
            ID (Идентификатор изменения)
            Описание (Описание изменения)
            Приоритет (Приоритет изменения)
            Статус (Статус изменения, например, запрошено, в процессе, завершено)
            Сервис (Ссылка на таблицу "Сервисы")
            Ответственный (Ответственное лицо за изменение)
            * */
            Statement stmt3 = connection.createStatement();
            stmt3.executeUpdate("CREATE TABLE IF NOT EXISTS Service (" +
                    "Service_ID INTEGER PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "Description VARCHAR(255), "+
                    "Responsible_ID  INTEGER "+
//                    "FOREIGN KEY (Responsible_ID)  REFERENCES companies (USER_ID)"+
                    ");");

            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public ChangesDAO getChangeDAO() {
        return new SQLiteChangesDAO();
    }

    @Override
    public ServicesDAO getServicesDAO() {
        return new SQLiteServicesDAO();
    }


    @Override
    public UserDAO getUserDAO() {
        return new SQLiteUserDAO();
    }
}
