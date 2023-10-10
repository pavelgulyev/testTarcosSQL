package com.example.demo1.Controller;

import com.example.demo1.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    public TableView<Changes> changesTableView;
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
    public ComboBox<String> comboServices;
    @FXML
    public ComboBox<String> comboFilter;
    @FXML
    public ComboBox<String> comboFilterValue;
    @FXML
    public VBox vboxNotForGuest;
    @FXML
    public TextArea textDescription;
    @FXML
    public CheckBox checkAuthor;
    @FXML
    public GridPane grid;
    public void selectSourceTab(MouseEvent mouseEvent) {
        TablePosition tablePosition = changesTableView.getSelectionModel().getSelectedCells().get(0);
        int row1 = tablePosition.getRow();
        changes = changesTableView.getItems().get(row1);
        textDescription.setText(changes.getDescriptionChange());
        for (String nameServices : comboServices.getItems()) {
            if (changes.getService().equals(nameServices)) {
                comboServices.setValue(nameServices);
            }
        }
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
    }
    public static Changes changes =new Changes();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboStatus.setItems(FXCollections.observableArrayList(StatusChangeEnum.values()));
        comboPriority.getItems().addAll(PriorityChangeEnum.values());
        ObservableList<String> elements = FXCollections.observableArrayList(
                "Статус",
                "Приоритет"
        );
        comboFilter.getItems().addAll(elements);
        comboFilterValue.setVisible(false);
        comboFilter.getSelectionModel().selectedItemProperty().addListener((options, oldValue, filter) -> {
            comboFilterValue.setVisible(true);
            fillComboboxValueFilter(filter);
//            System.out.println(filter);
        });
        try {
            comboServices.getItems().addAll(HomeViewController.daoFactory.getServicesDAO().getServicesString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!LoginController.isUserIn) {
            vboxNotForGuest.setVisible(false);
            grid.setVisible(false);
        }
        try {
            tableFill(HomeViewController.daoFactory.getChangeDAO().getAllChanges());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

//        RequirementsTable.setRowFactory(tableView -> {
//            final TableRow<Changes> row = new TableRow<>();
//            row.selectedProperty().addListener((observable) -> {
//                changes = row.getItem();
//                textDescription.setText(changes.getDescriptionChange());
//                for ( String nameServices: comboServices.getItems()) {
//                    if(changes.getService().equals(nameServices)){
//                        comboServices.setValue(nameServices);
//                    }
//                }
//                switch (changes.getPriority()) {
//                    case "Высокий" -> comboPriority.setValue(PriorityChangeEnum.valueOf("high"));
//                    case "Средний" -> comboPriority.setValue(PriorityChangeEnum.valueOf("middle"));
//                    case "Низкий" -> comboPriority.setValue(PriorityChangeEnum.valueOf("low"));
//                }
//                switch (changes.getStatusChange()) {
//                    case "Запрошено" -> comboStatus.setValue(StatusChangeEnum.valueOf("st1"));
//                    case "в Процессе" -> comboStatus.setValue(StatusChangeEnum.valueOf("st2"));
//                    case "Завершено" -> comboStatus.setValue(StatusChangeEnum.valueOf("st3"));
//                }
//            });
//            return row;
//        });
    }

    /**
     * Функция отображения в таблице
     * @param requirements отсортированный список требований
     * @throws SQLException
     */
    private void tableFill(ObservableList<Changes> requirements) throws SQLException {
        changesTableView.setItems(requirements);
        id_Change.setCellValueFactory(new PropertyValueFactory<>("id_Change"));
        PriorityChange.setCellValueFactory(new PropertyValueFactory<>("Priority"));
        StatusChange.setCellValueFactory(new PropertyValueFactory<>("StatusChange"));
        Description_Change.setCellValueFactory(new PropertyValueFactory<>("DescriptionChange"));
        Service_Change.setCellValueFactory(new PropertyValueFactory<>("Service"));
        ResponsibleChange.setCellValueFactory(new PropertyValueFactory<>("ResponsibleChange"));
    }

    public void Delete(ActionEvent actionEvent) throws SQLException, URISyntaxException, IOException {
        if (changesTableView.getSelectionModel().getSelectedItem() != null) {
            TableView.TableViewSelectionModel<Changes> selectionModel = changesTableView.getSelectionModel();
            int myIndex = changesTableView.getSelectionModel().getSelectedIndex();
            HomeViewController.daoFactory.getChangeDAO().deleteChanges(changesTableView.getSelectionModel().getSelectedItem());
            tableFill(HomeViewController.daoFactory.getChangeDAO().getAllChanges());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Выберете требование для удаления");
            alert.showAndWait();
        }
    }
    public void clear(){
        comboServices.setValue("");
        //comboServices.getSelectionModel().clearSelection();
        comboPriority.getSelectionModel().clearSelection();
        comboStatus.getSelectionModel().clearSelection();
        textDescription.setText("");
        checkAuthor.setSelected(false);
    }
    public void Update(ActionEvent actionEvent) throws SQLException, URISyntaxException, IOException {
        if(checkEmpty()) {
            String id = changes.getIdResponsibleChange();
            Users users;
            if (checkAuthor.isSelected()) {
                users = LoginController.user;
                System.out.println(LoginController.user.getLogin());
            } else {
                users = changes.ResponsibleChange;
            }
            changes.setDescriptionChange(textDescription.getText());
            changes.setPriority(comboPriority.getSelectionModel().getSelectedItem().toString());
            changes.setStatusChange(comboStatus.getSelectionModel().getSelectedItem().toString());
            changes.setServiceName(comboServices.getSelectionModel().getSelectedItem());
            changes.setResponsibleChange(users);
            HomeViewController.daoFactory.getChangeDAO().updateChanges(changes);
            tableFill(HomeViewController.daoFactory.getChangeDAO().getAllChanges());
            clear();
        }
    }
    public boolean checkEmpty(){
        if(comboStatus.getSelectionModel().isEmpty()){
            alertErrors("статус требования ");
            return false;
        }
        if(comboPriority.getSelectionModel().isEmpty()){
            alertErrors("приоритет требования");
            return false;
        }
        if(textDescription.getText().isEmpty()){
            alertErrors("описание изменения");
            return false;
        }
        return true;
    }
    public void alertErrors(String errors){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setContentText("Введите  "+errors);
        alert.showAndWait();
    }
    public void Add(ActionEvent actionEvent) throws SQLException, URISyntaxException, IOException {
        if(checkEmpty()) {
            changes.setDescriptionChange(textDescription.getText());
            changes.setPriority(comboPriority.getSelectionModel().getSelectedItem().toString());
            changes.setStatusChange(comboStatus.getSelectionModel().getSelectedItem().toString());
            changes.setServiceName(comboServices.getSelectionModel().getSelectedItem());
            changes.setResponsibleChange(LoginController.user);
            HomeViewController.daoFactory.getChangeDAO().addChanges(changes);
            tableFill(HomeViewController.daoFactory.getChangeDAO().getAllChanges());
            clear();
        }
    }

    public void onReset(ActionEvent actionEvent) throws SQLException, URISyntaxException, IOException {
        tableFill(HomeViewController.daoFactory.getChangeDAO().getAllChanges());
        comboFilter.setValue("");
        comboFilterValue.getItems().clear();
        comboFilterValue.setVisible(false);
        clear();
    }

    public void onFilter(ActionEvent actionEvent) throws SQLException, URISyntaxException, IOException {
        tableFill(HomeViewController.daoFactory.getChangeDAO().getAllChangesFilter(comboFilter.getValue(), comboFilterValue.getValue()));
    }
    private void  fillComboboxValueFilter(String filter){
        ObservableList<String> status = FXCollections.observableArrayList();
        status.add(String.valueOf(StatusChangeEnum.st1));
        status.add(String.valueOf(StatusChangeEnum.st2));
        status.add(String.valueOf(StatusChangeEnum.st3));
        ObservableList<String> priority = FXCollections.observableArrayList();
        priority.add(String.valueOf(PriorityChangeEnum.high));
        priority.add(String.valueOf(PriorityChangeEnum.middle));
        priority.add(String.valueOf(PriorityChangeEnum.low));
        switch (filter){
            case  "Статус" :
                comboFilterValue.getItems().clear();
                comboFilterValue.getItems().addAll(status);
                break;

            case  "Приоритет"  :
                comboFilterValue.getItems().clear();
                comboFilterValue.getItems().addAll(priority);
                break;

        }
    }
}
