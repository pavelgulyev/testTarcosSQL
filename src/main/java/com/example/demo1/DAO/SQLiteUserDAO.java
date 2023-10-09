package com.example.demo1.DAO;

import com.example.demo1.Model.Users;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class SQLiteUserDAO  implements UserDAO {
    Connection connection= SQLiteDAOFabrica.createConnection();
    @Override
    public List<Users> getAllEntity() throws SQLException, IOException {
        List <Users> list = new ArrayList<Users>();
        Users user = new Users();
        String sql = "SELECT * FROM User";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        int count = 0;
        while (result.next()) {
            user=Mapping(result);
            list.add(user);
            ++count;
        }
        connection.close();
        return list;
    }

    @Override
    public boolean Verification(String login, String password) throws SQLException {
        String sql = "SELECT * FROM User WHERE LOGIN='"+login+"' and PASSWORD='"+password+"';";
        System.out.println("Verification "+sql);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        int columns = result.getRow();
        int count = 0;
        while (result.next()) {
            ++count;
        }
        System.out.println(count);
        if(count==0){
            connection.close();
            return false;
        }
        else{
            connection.close();
            return true;
        }
    }

    @Override
    public int addUser(String login, String password) throws SQLException, IOException {
        PreparedStatement insert  = connection.prepareStatement("insert into User (LOGIN,PASSWORD) values (?,?);");
        insert.setString(1,login);
        insert.setString(2,password);
        insert.executeUpdate();
        return findUser(login,password).getId_User();
    }

    @Override
    public Users findUserByID(int id) throws SQLException, IOException {
        Users user = new Users();

        String sql = "SELECT * FROM User WHERE USER_ID='"+id+"';";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                user= Mapping(resultSet);
            }
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(SQLException logOrIgnore) {}
            if (statement != null) try { statement.close(); } catch (SQLException logOrIgnore) {}
            if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
        }
        return user;
    }

    @Override
    public Users findUser(String login, String password) throws SQLException, IOException {
        Users user = new Users();
        String sql = "SELECT * FROM User WHERE LOGIN='"+login+"' and PASSWORD='"+password+"';";
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                user= Mapping(resultSet);
            }
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException logOrIgnore) {}
            if (statement != null) try { statement.close(); } catch (SQLException logOrIgnore) {}
            if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
        }
        return user;
    }

    @Override
    public Users findUser(String login) throws SQLException, IOException {
        Users user = new Users();
        String sql = "SELECT * FROM User WHERE LOGIN='"+login+"';";
        System.out.println(sql);
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                user= Mapping(resultSet);
            }
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException logOrIgnore) {}
            if (statement != null) try { statement.close(); } catch (SQLException logOrIgnore) {}
            if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
        }
        return user;

    }

    @Override
    public Users Mapping(ResultSet result) throws SQLException, IOException {
        ResultSet resultSet = result;
        Users user = new Users();
        user.setId_User(result.getInt("USER_ID"));
        user.setLogin(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        return user;
    }

}
