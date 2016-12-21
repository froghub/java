package local.shop.controller;


import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import local.shop.controller.CheckUtil;
import local.shop.controller.ModalWinController;
import local.shop.model.deprecated.ItemCheck;

public class CheckEditController implements ModalWinController{
    private Stage stage;
    private static ItemCheck sourceCheck;


    @FXML
    private TableView<ItemCheck.Entry> checkItems;
    @FXML
    private TableColumn<ItemCheck.Entry , Integer> id;
    @FXML
    private TableColumn<ItemCheck.Entry, String> name;
    @FXML
    private TableColumn<ItemCheck.Entry , Integer> count;
    @FXML
    private TableColumn<ItemCheck.Entry , Float> price;
    @FXML
    private Label sumLabel;


    @FXML
    public void initialize() {
        sumLabel.setText("");

        id.setCellValueFactory(cellData->(ObservableValue)cellData.getValue().getItem().idProperty());
        name.setCellValueFactory(cellData->(ObservableValue)cellData.getValue().getItem().nameProperty());
        count.setCellValueFactory(cellData->(ObservableValue)cellData.getValue().countProperty());
        price.setCellValueFactory(cellData->(ObservableValue)cellData.getValue().getItem().priceProperty());
        initTable();
    }


    @FXML
    public void incItem(){
        int index = checkItems.getSelectionModel().getSelectedIndex();
        if (index>=0&&sourceCheck.getItems().get(index).getCount()<sourceCheck.getItems().get(index).getItem().getCountStored()){
            sourceCheck.getItems().get(index).addCount(1);
        }
        checkItems.refresh();
    }
    @FXML
    public void decItem(){
        int index = checkItems.getSelectionModel().getSelectedIndex();
        if (index>=0&&sourceCheck.getItems().get(index).getCount()>0){
            sourceCheck.getItems().get(index).reduceCount(1);
        }
        checkItems.refresh();
    }
    @FXML
    public void applyChanges(){
        CheckUtil.reclamationUpdate(sourceCheck) ;
        stage.close();
    }


    @FXML
    public void cancelChanges(){
        stage.close();
    }

    public static void setSourceCheck(ItemCheck check){
        sourceCheck = check;
    }

    public void initTable() {

        sourceCheck = CheckUtil.loadCheckData(sourceCheck);
        checkItems.setItems(sourceCheck.getItems());
    }
    @Override
    public void setStage(Stage stage) {
        this.stage=stage;
    }
}
