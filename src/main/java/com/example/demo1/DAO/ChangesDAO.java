package com.example.demo1.DAO;

import com.example.demo1.Model.Changes;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Интерфейс доступа к данным
 */
public interface ChangesDAO {
    ObservableList<Changes> getAllChanges() throws SQLException, URISyntaxException, IOException;

    ObservableList<Changes> getAllChangesFilter(String filter, String value) throws SQLException, URISyntaxException, IOException;

    Changes getChangesById(String id) throws SQLException, URISyntaxException, IOException;
    void addChanges(Changes changes) throws SQLException;
    void updateChanges(Changes changes) throws SQLException;
    void deleteChanges(Changes changes) throws SQLException ;

}
