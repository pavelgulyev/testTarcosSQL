package com.example.demo1.DAO;

import com.example.demo1.Model.Changes;
import com.example.demo1.Model.Users;
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
        Changes requirements = new Changes();
        while (set.next()) {
            requirements = MappingChanges(set);
            res.add(requirements);
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
    public void addChanges(Changes changes) {

    }

    @Override
    public void updateChanges(Changes changes) throws SQLException {
        Changes requirements = changes;
        PreparedStatement insert  = connection.prepareStatement("update Change set Name = ?, Priority = ?, " +
                "Status = ?, Author=?, Type=?, " +
                "Complexity=?, Source=?, Reason=?, Description=?, RiskAssessment=?  where Requirements_ID = ?");
//        insert.setString(1,requirements.getName());
        insert.setString(2,requirements.getPriority());
        insert.setString(3,requirements.getStatusChange());
//        insert.setString(4,requirements.getAuthor());
//        insert.setString(5,requirements.getType());
//        insert.setString(6,requirements.getComplexity());
//        insert.setString(7,requirements.getSource());
//        insert.setString(8,requirements.getReason());
//        insert.setString(9,requirements.getDescription());
//        insert.setString(10,requirements.getRiskAssessment());
//        insert.setString(11, requirements.getId_Requirements());
        insert.executeUpdate();
    }

    @Override
    public void deleteChanges(Changes changes) throws SQLException {
        Changes changes1=(Changes) changes;
        PreparedStatement insert  = connection.prepareStatement("delete from Requirements where Requirements_ID = ?");
        insert.setString(1, String.valueOf(changes1.getId_Change()));
        insert.executeUpdate();
    }
}
