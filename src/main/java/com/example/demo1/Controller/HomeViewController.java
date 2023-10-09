package com.example.demo1.Controller;

import com.example.demo1.DAO.DAOFabrica;
import com.example.demo1.HelloApplication;
import javafx.event.ActionEvent;

import java.io.IOException;

public class HomeViewController{
    public static DAOFabrica daoFactory;

    public void onDataBase(ActionEvent event) throws IOException {
        daoFactory = DAOFabrica.getDAOFactory("база данных");
        HelloApplication.downloadScene("login-view.fxml", event, "Страница входа");
    }
    public void onTxt(ActionEvent event) throws IOException  {
        daoFactory = DAOFabrica.getDAOFactory("файл");
        HelloApplication.downloadScene("login-view.fxml", event, "Страница входа");
    }

    public void onRam(ActionEvent event) throws IOException {
        daoFactory = DAOFabrica.getDAOFactory("временно");
        HelloApplication.downloadScene("login-view.fxml", event, "Страница входа");
    }
}
