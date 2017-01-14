package local.shop.controller;


import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import local.shop.controller.CheckUtil;
import local.shop.controller.ModalWinController;
import local.shop.model.deprecated.ItemCheck;
import local.shop.model.entity.ChecksEntity;
import local.shop.model.entity.SolditemsEntity;

public class CheckEditController implements ModalWinController{
    private Stage stage;
    private static ChecksEntity sourceCheck;


    @FXML
    private TableView<SolditemsEntity> checkItems;
    @FXML
    private TableColumn<SolditemsEntity, Integer> id;
    @FXML
    private TableColumn<SolditemsEntity, String> name;
    @FXML
    private TableColumn<SolditemsEntity , Integer> count;
    @FXML
    private TableColumn<SolditemsEntity , Float> price;
    @FXML
    private Label sumLabel;


    @FXML
    public void initialize() {
        sumLabel.setText("");

        id.setCellValueFactory(cellData -> ((ObservableValue)new SimpleIntegerProperty(cellData.getValue().getProduct().getId())));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduct().getName()));
        price.setCellValueFactory(cellData -> ((ObservableValue)new SimpleFloatProperty(cellData.getValue().getProduct().getPrice())));
        count.setCellValueFactory(cellData -> ((ObservableValue) new SimpleIntegerProperty(cellData.getValue().getCount())));

        initTable();
    }


    @FXML
    public void incItem(){
        int index = checkItems.getSelectionModel().getSelectedIndex();
        int count = sourceCheck.getItems().get(index).getCount();
        if (index>=0&&count<sourceCheck.getItems().get(index).getProduct().getCount()){
            sourceCheck.getItems().get(index).setCount((short)++count);
        }
        checkItems.refresh();
    }
    @FXML
    public void decItem(){
        int index = checkItems.getSelectionModel().getSelectedIndex();
        int count = sourceCheck.getItems().get(index).getCount();
        if (index>=0&&count>0){
            sourceCheck.getItems().get(index).setCount((short)--count);
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

    public static void setSourceCheck(ChecksEntity check){
        sourceCheck = check;
    }

    public void initTable() {

        sourceCheck = CheckUtil.loadCheckData(sourceCheck);
        checkItems.setItems(sourceCheck.toObservableList());
    }
    @Override
    public void setStage(Stage stage) {
        this.stage=stage;
    }
}
