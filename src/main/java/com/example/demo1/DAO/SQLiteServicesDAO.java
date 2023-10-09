package com.example.demo1.DAO;

import com.example.demo1.Model.Services;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;

public class SQLiteServicesDAO implements ServicesDAO {
    Connection connection = SQLiteDAOFabrica.createConnection();
    @Override
    public ObservableList<Services> getServices() throws SQLException, URISyntaxException, IOException {
        return null;
    }

    @Override
    public Services MappringService(ResultSet result) throws SQLException, URISyntaxException, IOException {
        ResultSet resultSet =  result;
        Services user = new Services();
        user.setId_Service(resultSet.getInt(1));
        user.setNameService(resultSet.getString(2));
        user.setDescriptionService(resultSet.getString(3));
        user.setResponsibleService(resultSet.getString(4));
        return user;
    }
    @Override
    public Services getServiceID(String id) throws SQLException, URISyntaxException, IOException {
        Services services = new Services();
        String sql=  "SELECT * FROM Service WHERE Service_ID="+id+";";
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                services= MappringService(resultSet);
            }
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException logOrIgnore) {}
            if (statement != null) try { statement.close(); } catch (SQLException logOrIgnore) {}
            if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
        }
        return services;
    }
}
