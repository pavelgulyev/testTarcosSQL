package com.example.demo1.Model;

import com.example.demo1.Controller.HomeViewController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class Changes {
    /*
    * ID (Идентификатор изменения)
Описание (Описание изменения)
Приоритет (Приоритет изменения)
Статус (Статус изменения, например, запрошено, в процессе, завершено)
Сервис (Ссылка на таблицу "Сервисы")
Ответственный (Ответственное лицо за изменение)*/
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

//    public Services getService() {
//        return Service;
//    }
    public String getService() {
        return Service.getNameService();
    }
    public void setService(String service) throws SQLException, URISyntaxException, IOException {
        this.Service= HomeViewController.daoFactory.getServicesDAO().getServiceID(service);

    }
    public void setService(Services service) {
        Service = service;
    }

//    public Users getResponsibleChange() {
//        return ResponsibleChange;
//    }
    public String getResponsibleChange() {
        return ResponsibleChange.getLogin();
    }
    public void setResponsibleChange(String responsibleChange) throws SQLException, URISyntaxException, IOException {

        this.ResponsibleChange= HomeViewController.daoFactory.getUserDAO().findUserByID(Integer.parseInt(responsibleChange));
    }
    public void setResponsibleChange(Users responsibleChange) {
        ResponsibleChange = responsibleChange;
    }

}
