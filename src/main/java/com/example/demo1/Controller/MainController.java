package com.example.demo1.Controller;

import com.example.demo1.Model.Changes;
import com.example.demo1.Model.PriorityChangeEnum;
import com.example.demo1.Model.StatusChangeEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public TableView<Changes> RequirementsTable;
    @FXML
    public TableColumn<Changes, String> id_Change;
    @FXML
    public TableColumn<Changes, String> Description_Change;
    @FXML
    public TableColumn<Changes, String> PriorityChange;
    @FXML
    public TableColumn<Changes, String> Service_Change;
    @FXML
    public TableColumn<Changes, String> StatusChange;
    @FXML
    public TableColumn<Changes, String> ResponsibleChange;
    @FXML
    public ComboBox<StatusChangeEnum> comboStatus;
    @FXML
    public ComboBox<PriorityChangeEnum> comboPriority;
    @FXML
    public VBox vboxNotForGuest;
    @FXML
    public TextArea textDescription;
    @FXML
    public GridPane grid;
    public void selectSourceTab(MouseEvent mouseEvent) {

    }
    public static Changes changes =new Changes();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboStatus.setItems(FXCollections.observableArrayList(StatusChangeEnum.values()));
        comboPriority.getItems().addAll(PriorityChangeEnum.values());
        if(!LoginController.isUserIn) {
            vboxNotForGuest.setVisible(false);
            grid.setVisible(false);
        }
        try {
            tableView(HomeViewController.daoFactory.getChangeDAO().getAllChanges());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequirementsTable.setRowFactory(tableView -> {
            final TableRow<Changes> row = new TableRow<>();
            row.selectedProperty().addListener((observable) -> {
                changes = row.getItem();
                textDescription.setText(changes.getDescriptionChange());
               // System.out.println("Пользователь Login="+ changes.ResponsibleChange.getLogin());
                switch (changes.getPriority()) {
                    case "Высокий" -> comboPriority.setValue(PriorityChangeEnum.valueOf("high"));
                    case "Средний" -> comboPriority.setValue(PriorityChangeEnum.valueOf("middle"));
                    case "Низкий" -> comboPriority.setValue(PriorityChangeEnum.valueOf("low"));
                }
                switch (changes.getStatusChange()) {
                    case "Запрошено" -> comboStatus.setValue(StatusChangeEnum.valueOf("st1"));
                    case "в Процессе" -> comboStatus.setValue(StatusChangeEnum.valueOf("st2"));
                    case "Завершено" -> comboStatus.setValue(StatusChangeEnum.valueOf("st3"));
                }
            });

            return row;
        });
    }
    /**
     * Функция отображения в таблице
     * @param requirements отсортированный список требований
     * @throws SQLException
     */
    private void tableView(ObservableList<Changes> requirements) throws SQLException {
        RequirementsTable.setItems(requirements);
        id_Change.setCellValueFactory(new PropertyValueFactory<>("id_Change"));
        PriorityChange.setCellValueFactory(new PropertyValueFactory<>("Priority"));
        StatusChange.setCellValueFactory(new PropertyValueFactory<>("StatusChange"));
        Description_Change.setCellValueFactory(new PropertyValueFactory<>("DescriptionChange"));
        Service_Change.setCellValueFactory(new PropertyValueFactory<>("Service"));
        ResponsibleChange.setCellValueFactory(new PropertyValueFactory<>("ResponsibleChange"));
    }
}
