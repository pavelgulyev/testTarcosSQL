package com.example.demo1.DAO;

import com.example.demo1.Model.Users;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс Dao для работы с БД
 */
public interface UserDAO {
    /**
     * Функция получения всех записей
     * @return список сущностей
     * @throws SQLException исключение при работе с SQL-запросами
     */
    List<Users> getAllEntity() throws SQLException, IOException;

    /**
     * Функция верификации
     * @param login логин
     * @param password пароль
     * @return true or false
     * @throws SQLException исключение при работе с SQL-запросами
     */
    public boolean Verification(String login, String password) throws SQLException, IOException;

    /**
     *  Функция добавления записи
     * @param login  login
     * @param password password
     * @throws SQLException исключение при работе с SQL-запросами
     */
    int addUser(String login, String password) throws SQLException, IOException;

    /**
     * Функция поиска поользователя по id
     * @param id int
     * @return пользователь
     * @throws SQLException исключение при работе с SQL-запросами
     */

    public Users findUserByID(int id) throws SQLException, IOException;

    /**
     * Функция поиска поользователя по login, password
     * @param login  login
     *  @param password password
     * @return пользователь
     * @throws SQLException исключение при работе с SQL-запросами
     */
    public Users findUser(String login, String password) throws SQLException, IOException;

    /**
     * Функция поиска поользователя по login
     * @param login  logi
     * @return пользователь
     * @throws SQLException исключение при работе с SQL-запросами
     */
    public Users findUser(String login) throws SQLException, IOException;

    /**
     * Маппинг результата апроса
     * @param result
     * @return  Users
     * @throws SQLException исключение при работе с SQL-запросами
     */
    public Users Mapping(ResultSet result) throws SQLException, IOException;

}
