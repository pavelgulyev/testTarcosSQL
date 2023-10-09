package com.example.demo1.Model;

public class Users {
    int id_User;
    String login;
    String password;
    /**
     * Функия возвращает id_User
     * @return id_User
     */
    public int getId_User() {
        return id_User;
    }

    /**
     * Функия задает значение
     * @param id_User id
     */
    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    /**
     * Функия возвращает login
     * @return login
     */
    public String getLogin() {
        return login;
    }
    /**
     * Функия задает login
     * @param  login login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Функия возвращает password
     * @return password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Функия задает password
     * @param  password password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString(){
        return "User "+id_User+" login="+login+" passsword="+password;
    }



}
