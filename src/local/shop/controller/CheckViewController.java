package local.shop.controller;


import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import local.shop.DAO.DBConnector;
import local.shop.MainWindow;
import local.shop.model.deprecated.ArchivedCheck;
import local.shop.model.deprecated.ItemCheck;
import local.shop.model.entity.ChecksEntity;

public class CheckViewController implements ModalWinController {
    Stage stage;


    @FXML
    private TableView<ChecksEntity> items;
    @FXML
    private TableColumn<ChecksEntity, Integer> id;
    @FXML
    private TableColumn<ChecksEntity, Float> total;
    @FXML
    private TableColumn<ChecksEntity, String> date;

    @FXML
    public void initialize() {
        id.setCellValueFactory(cellData -> ((ObservableValue) new SimpleIntegerProperty(cellData.getValue().getId())));
        total.setCellValueFactory(cellData ->(ObservableValue) new SimpleFloatProperty(cellData.getValue().getTotal()));
        date.setCellValueFactory(cellData ->   new SimpleStringProperty(""+cellData.getValue().getDate()));
        loadTable();
    }


    @FXML
    public void editSelectedCheck(){
        int index = items.getSelectionModel().getSelectedIndex();
        if (index>=0){
            ModalWinController controller;
            CheckEditController.setSourceCheck(items.getItems().get(index));
            controller=MainWindow.getInstance().initModalWindow("CheckEditWindow.fxml","Редактирование чека",this.stage);
            loadTable();
        }
    }

    @FXML
    public void closeWindow() {
        stage.close();
    }
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void loadTable() {
        items.setItems(FXCollections.observableArrayList(DBConnector.getCheckDAO().select()));
    }
}
