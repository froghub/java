package local.shop.controller;


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

public class CheckViewController implements ModalWinController {
    Stage stage;


    @FXML
    private TableView<ItemCheck> items;
    @FXML
    private TableColumn<ArchivedCheck, Integer> id;
    @FXML
    private TableColumn<ArchivedCheck, Float> total;
    @FXML
    private TableColumn<ArchivedCheck, String> date;

    @FXML
    public void initialize() {


        id.setCellValueFactory(cellData -> ((ObservableValue) cellData.getValue().idProperty()));
        total.setCellValueFactory(cellData ->(ObservableValue) cellData.getValue().sumProperty());
        date.setCellValueFactory(cellData ->   cellData.getValue().dateProperty());
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
