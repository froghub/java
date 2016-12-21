package local.shop.controller;


import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import local.shop.controller.ItemUtil;
import local.shop.model.deprecated.ShopItem;
import local.shop.model.entity.ProductsEntity;

import java.util.List;

public class ViewStoredItemsController {
    private Stage stage;
    @FXML
    private TableView<ShopItem> items;
    @FXML
    private TableColumn<ShopItem, Integer> id;
    @FXML
    private TableColumn<ShopItem, String> name;
    @FXML
    private TableColumn<ShopItem, Integer> countStored;
    @FXML
    private TableColumn<ShopItem, Float> price;
    @FXML
    private TextField searchText;
    @FXML
    private Label lFound;

    @FXML
    public void initialize() {

        id.setCellValueFactory(cellData -> ((ObservableValue) cellData.getValue().idProperty()));
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        price.setCellValueFactory(cellData -> ((ObservableValue) cellData.getValue().priceProperty()));
        countStored.setCellValueFactory(cellData -> ((ObservableValue) cellData.getValue().countStoredProperty()));
    }

    @FXML
    public void search() {
        String searchable = searchText.getText();
        ObservableList found;
        if (searchable.trim().isEmpty()) {
            lFound.setText("Найдено: 0");
        } else {
            List<ProductsEntity> searchResult = ItemUtil.searchByName(searchable);
            if (searchResult.size() > 0) {
                lFound.setText("Найдено: " + searchResult.size());
                found = FXCollections.observableArrayList(searchResult);
                items.setItems(found);
            } else {
                lFound.setText("Найдено: 0");
            }
        }
    }

    @FXML
    public void close() {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
