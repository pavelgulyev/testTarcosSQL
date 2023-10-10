package com.example.demo1.Model;

import com.example.demo1.Controller.HomeViewController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class Changes {

    public int id_Change;
    public String StatusChange;
    public String  Priority;
    public String DescriptionChange;
    public Services Service;
    public Users ResponsibleChange;

    public Changes(){
        Service = new Services();
        ResponsibleChange=new Users();
    }
    public int getId_Change() {
        return id_Change;
    }

    public void setId_Change(int id_Change) {
        this.id_Change = id_Change;
    }

    public String getStatusChange() {
        return StatusChange;
    }

    public void setStatusChange(String statusChange) {
        StatusChange = statusChange;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getDescriptionChange() {
        return DescriptionChange;
    }

    public void setDescriptionChange(String descriptionChange) {
        DescriptionChange = descriptionChange;
    }

    public String getIdService() {
        return String.valueOf(Service.getId_Service());
    }
    public String getService() {
        return Service.getNameService();
    }
    public void setServiceName(String name) throws SQLException, URISyntaxException, IOException {
        this.Service= HomeViewController.daoFactory.getServicesDAO().getServiceName(name);
    }
    public void setService(String service) throws SQLException, URISyntaxException, IOException {
        this.Service= HomeViewController.daoFactory.getServicesDAO().getServiceID(service);
    }
    public void setService(Services service) {
        Service = service;
    }
    public String getResponsibleChange() {
        return ResponsibleChange.getLogin();
    }
    public String getIdResponsibleChange() {
        return String.valueOf(ResponsibleChange.getId_User());
    }
    public void setResponsibleChange(String responsibleChange) throws SQLException, URISyntaxException, IOException {

        this.ResponsibleChange= HomeViewController.daoFactory.getUserDAO().findUserByID(Integer.parseInt(responsibleChange));
    }
    public void setResponsibleChange(Users responsibleChange) {
        ResponsibleChange = responsibleChange;
    }

}
