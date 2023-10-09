package com.example.demo1.DAO;

public class TxtFileDAOFactory extends DAOFabrica {
    @Override
    public ChangesDAO getChangeDAO() {
        return null;
    }

    @Override
    public ServicesDAO getServicesDAO() {
        return null;
    }

    @Override
    public UserDAO getUserDAO() {
        return null;
    }
}
