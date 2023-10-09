package com.example.demo1.Model;

import com.example.demo1.Controller.HomeViewController;

import java.io.IOException;
import java.sql.SQLException;

public class Services {
    int id_Service;
    public String nameService;
    public String DescriptionService;
    public Users ResponsibleService;

    /**
     *
     * @return id_Service
     */
    public int getId_Service() {
        return id_Service;
    }

    public void setId_Service(int id_Service) {
        this.id_Service = id_Service;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getDescriptionService() {
        return DescriptionService;
    }

    public void setDescriptionService(String descriptionService) {
        DescriptionService = descriptionService;
    }

    public Users getResponsibleService() {
        return ResponsibleService;
    }

    public void setResponsibleService(Users responsibleService) {
        ResponsibleService = responsibleService;
    }
    public void setResponsibleService(String responsibleService) throws SQLException, IOException {
        this.ResponsibleService= HomeViewController.daoFactory.getUserDAO().findUser(responsibleService);
    }
}