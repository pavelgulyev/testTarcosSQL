package com.example.demo1.DAO;

import com.example.demo1.Model.Changes;

import java.util.List;
import java.util.Random;

public class ListChanges extends DAOFabrica {
    private List<Changes> changes;

    public ListChanges(int size) {

    }



    private String randomTime(Random random) {
        int hours = random.nextInt(24);
        int minutes = random.nextInt(60);
        return String.format("%02d:%02d", hours, minutes);
    }

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
