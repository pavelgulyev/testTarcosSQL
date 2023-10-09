package com.example.demo1.DAO;

import com.example.demo1.Model.Changes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;

public class SQLiteChangesDAO implements ChangesDAO {
    Connection connection = SQLiteDAOFabrica.createConnection();

    /**
     *
     * @return
     * @throws SQLException
     * @throws URISyntaxException
     * @throws IOException
     */
    @Override
    public ObservableList<Changes> getAllChanges() throws SQLException, URISyntaxException, IOException {
        ObservableList<Changes> res = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("Select * from Change;");
        System.out.println("row Change-"+set.getRow());
        Changes changes = new Changes();
        while (set.next()) {
            changes = MappingChanges(set);
            res.add(changes);
        }
        return res;
    }
    @Override
    public ObservableList<Changes> getAllChangesFilter(String filter, String value) throws SQLException, URISyntaxException, IOException {
        ObservableList<Changes> res = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();

        String sql=null;
        switch(filter){
            case  "Статус" :
                sql = "Select * from Change where status='"+value+"';";
                break;
            case  "Приоритет" :
                sql = "Select * from Change where priority='"+value+"';";
                break;
        }

        ResultSet set = statement.executeQuery(sql);
        System.out.println("sql ChangesFilter-"+sql);
        Changes changes = new Changes();
        while (set.next()) {
            changes = MappingChanges(set);
            res.add(changes);
        }
        return res;
    }
    private Changes MappingChanges(ResultSet result1) throws SQLException, URISyntaxException, IOException {
        ResultSet result = (ResultSet) result1;
        Changes changes = new Changes();
        changes.setId_Change(Integer.parseInt(result.getString("Change_ID")));
        changes.setDescriptionChange(result.getString("Description"));
        changes.setPriority(result.getString("priority"));
        changes.setStatusChange(result.getString("Status"));
        changes.setService(result.getString("Services_Id"));
        changes.setResponsibleChange(result.getString("User_Id"));
        return changes;
    }

    @Override
    public Changes getChangesById(String id) throws SQLException, URISyntaxException, IOException {
        Changes user;
        String sql = "SELECT * FROM Change WHERE Change_ID='"+id+";";
        System.out.println(sql);
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet result = null;
        result = statement.executeQuery(sql);
        int count=0;
        while (result.next()) {
            ++count;
        }
        System.out.println(count);
        if(count==0){
            connection.close();
            return null;
        }
        else{
            user =MappingChanges(result);
            connection.close();
            return user;
        }
    }

    @Override
    public void addChanges(Changes changes) throws SQLException {
        Changes requirements = changes;
        PreparedStatement insert  = connection.prepareStatement("insert into Change (Description,priority,status," +
                "Services_Id,User_Id) " +
                "values(?,?,?,?,?);");
        insert.setString(1,requirements.getDescriptionChange());
        insert.setString(2,requirements.getPriority());
        insert.setString(3,requirements.getStatusChange());
        insert.setString(4,requirements.getIdService());
        insert.setString(5,requirements.getIdResponsibleChange());
        insert.executeUpdate();

    }

    @Override
    public void updateChanges(Changes changes) throws SQLException {
        Changes requirements = changes;
        PreparedStatement insert  = connection.prepareStatement("update Change set Description = ?, priority = ?, " +
                "status = ?, Services_Id=?, User_Id=? " +
                "  where Change_ID = ?");
        insert.setString(1,requirements.getDescriptionChange());
        insert.setString(2,requirements.getPriority());
        insert.setString(3,requirements.getStatusChange());
        insert.setString(4,requirements.getIdService());
        insert.setString(5,requirements.getIdResponsibleChange());
        insert.setString(6, String.valueOf(requirements.getId_Change()));
        insert.executeUpdate();
    }

    @Override
    public void deleteChanges(Changes changes) throws SQLException {
        Changes changes1=(Changes) changes;
        PreparedStatement insert  = connection.prepareStatement("delete from Change where Change_ID = ?");
        insert.setString(1, String.valueOf(changes1.getId_Change()));
        insert.executeUpdate();
    }
}
