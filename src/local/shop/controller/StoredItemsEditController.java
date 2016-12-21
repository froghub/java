package local.shop.controller;



import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import local.shop.DAO.DBConnector;
import local.shop.model.deprecated.ShopItem;



public class StoredItemsEditController {

    private int newId;
    private String newName;
    private float newPrice;
    private int newCount;


    @FXML
    private TableView<ShopItem> items;
    @FXML
    private TableColumn<ShopItem, Integer> tId;
    @FXML
    private TableColumn<ShopItem, String> tName;
    @FXML
    private TableColumn<ShopItem, Integer> tCount;
    @FXML
    private TableColumn<ShopItem, Float> tPrice;

    @FXML
    private TextField fId;
    @FXML
    private TextField fName;
    @FXML
    private TextField fPrice;
    @FXML
    private TextField fCount;
    @FXML
    private Label errLabel;

    private ObservableList<ShopItem> list;

    @FXML
    public void initialize() {

        tId.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().idProperty());
        tName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tCount.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().countStoredProperty());
        tPrice.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().priceProperty());

    }

    @FXML
    public void saveChanges() {
        if (!fId.getText().equals("")) {
            parseInputFields();
            DBConnector.getItemDAO().update(new ShopItem(newId, newName, newPrice, newCount));
            fId.setText("");
            fName.setText("");
            fPrice.setText("");
            fCount.setText("");
        }
        else errLabel.setText("Невозможно сохранить");

    }



    @FXML
    public void addNew() {
       if(parseInputFields()) {
           DBConnector.getItemDAO().insert(new ShopItem(0,newName,newPrice,newCount));
           fId.setText("");
           fName.setText("");
           fPrice.setText("");
           fCount.setText("");
       } /*else {
          errLabel.setText("Не все поля заполнены");
       }*/
    }

    @FXML
    public void loadList() {

        list = FXCollections.observableArrayList(DBConnector.getItemDAO().select());
        items.setItems(list);
        //items.setItems(list);
    }

    private boolean parseInputFields() {
        boolean flag = true;
        errLabel.setText("");
        try{
            newPrice = Float.parseFloat(fPrice.getText());
        }catch (NumberFormatException e){
            errLabel.setText("Цена заполнена неверно");
            flag=false;
        }
        try{
            newCount = Integer.parseInt(fCount.getText());
        }catch (NumberFormatException e){
            errLabel.setText("Количество заполнено неверно");
            flag=false;
        }
        if (!fId.getText().equals("")) {
            newId = Integer.parseInt(fId.getText());
        }
        newName = fName.getText();

        return flag;
    }
    @FXML
    public void selectItem() {
        int index = items.getSelectionModel().getSelectedIndex();
        if (index>=0) {
            ShopItem item = items.getItems().get(index);
            fId.setText("" + item.getId());
            fName.setText(item.getName());
            fPrice.setText("" + item.getPrice());
            fCount.setText("" + item.getCountStored());
        }
    }

}
