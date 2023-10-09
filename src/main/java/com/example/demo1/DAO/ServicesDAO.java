package com.example.demo1.DAO;

import com.example.demo1.Model.Services;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ServicesDAO {
    /**
     *Функция получения всех записей
     * @return {@link  ObservableList} список требований
     * @throws IOException исключение потока для чтения данных
     * @throws SQLException исключение при работе с SQL-запросами
     */
    ObservableList<Services> getServices() throws SQLException, URISyntaxException, IOException;

    /**
     * Маппинг результата апроса
     * @param result результирующий список
     * @return требование
     * @throws SQLException исключение при работе с SQL-запросами
     * @throws URISyntaxException исключение при получении URL
     */

    Services MappringService(ResultSet result) throws SQLException, URISyntaxException, IOException;

    Services getServiceID(String id) throws SQLException, URISyntaxException, IOException;


}
