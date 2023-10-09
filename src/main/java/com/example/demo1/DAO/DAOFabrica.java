package com.example.demo1.DAO;

public abstract class DAOFabrica {
    public static String BD="база данных";
    public static String FILE="файл";
    public static String RAM="временно";

    public abstract ChangesDAO getChangeDAO();
    public abstract ServicesDAO getServicesDAO();
    public abstract UserDAO getUserDAO();

    public static DAOFabrica getDAOFactory(
            String type) {
        if (type.equalsIgnoreCase(BD)) {
            return new SQLiteDAOFabrica();
        } else if (type.equalsIgnoreCase(FILE)) {
            return new TxtFileDAOFactory();//имя файла
        } else if (type.equalsIgnoreCase(RAM)) {
            return new ListChanges(10);//генератор на 10 задач
        } else {
            throw new IllegalArgumentException("Invalid datasource type!");
        }
//        switch (whichFactory) {
//            case FILE:
//                return new TxtFileDAOFactory();
//            case BD:
//                return new SQLiteDAOFabrica();
//            case RAM:
//                return new SQLiteDAOFabrica();
//            default           :
//                return null;
//        }
    }
}
