package com.example.demo1.Controller;

import com.example.demo1.HelloApplication;
import com.example.demo1.Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField LoginText;
    @FXML
    private TextField PasswordText;
    public static Users user;
    /**
     * Функция входа как пользователь
     * @param actionEvent событие
     * @throws SQLException  исключение при работе с SQL-запросами
     * @throws IOException исключение потока для чтения данных
     */
    public void onLogin(ActionEvent actionEvent)  throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("mu");
        MainController main = new MainController();
        System.out.println(LoginText.getText());
        System.out.println(PasswordText.getText());
        if (HomeViewController.daoFactory.getUserDAO().Verification(LoginText.getText(), PasswordText.getText()))
        {
            user=HomeViewController.daoFactory.getUserDAO().findUser(LoginText.getText(), PasswordText.getText());
            //System.out.println("LoginController user.login="+user.getLogin());
            stage.hide();
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            LoginText.clear();
            PasswordText.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка авторизации");
            alert.setContentText("Неверные данные");
            alert.showAndWait();
        }

    }

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void onShowRegister(ActionEvent actionEvent) throws IOException {
        HelloApplication.downloadScene("Registration-view.fxml", actionEvent, "Страница регистрации");
    }

    public static boolean isUserIn = true;

    /**
     * Функция для входа как гость
     * @param actionEvent событие
     * @throws IOException исключение потока для чтения данных
     */
    public void onGuest(ActionEvent actionEvent) throws IOException {
        isUserIn = false;
        HelloApplication.downloadScene("project-list.fxml", actionEvent, "Только просмотр (гостевой вход)");
    }
}