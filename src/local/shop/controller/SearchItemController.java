package local.shop.controller;


import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import local.shop.model.entity.ChecksEntity;
import local.shop.model.entity.ProductsEntity;

import java.util.List;

public class SearchItemController {

    private Stage stage;
    private ChecksEntity currentCheck;
    private ObservableList<ProductsEntity> found;

    @FXML
    private TextField searchText;
    @FXML
    private TextField addCount;
    @FXML
    private Label labelSearchResult;
    @FXML
    private Label labelConfirm;
    @FXML
    private TableView<ProductsEntity> items;
    @FXML
    private TableColumn<ProductsEntity, Integer> id;
    @FXML
    private TableColumn<ProductsEntity, String> name;
    @FXML
    private TableColumn<ProductsEntity, Integer> countStored;
    @FXML
    private TableColumn<ProductsEntity, Float> price;

    @FXML
    public void initialize() {
        addCount.setText("" + 0);
        id.setCellValueFactory(cellData -> ((ObservableValue) new SimpleIntegerProperty(cellData.getValue().getId())));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        price.setCellValueFactory(cellData -> ((ObservableValue) new SimpleFloatProperty(cellData.getValue().getPrice())));
        countStored.setCellValueFactory(cellData -> ((ObservableValue) new SimpleIntegerProperty(cellData.getValue().getCount())));
    }

    @FXML
    public void search() {
        String searchable = searchText.getText().toLowerCase().trim();

        if (searchable.isEmpty()) {
            labelSearchResult.setText("Найдено: 0");
        } else {
             List<ProductsEntity> searchResult = ItemUtil.searchByNameExcludeZeroCount(searchable);

            if (searchResult.size() > 0) {
                labelSearchResult.setText(" Найдено: " + searchResult.size());
                found = FXCollections.observableArrayList(searchResult);
                items.setItems(found);
            } else {
                labelSearchResult.setText("Найдено: 0");
            }
        }
    }

    @FXML
    public void addToCheck() {
        int selectedIndex = items.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            labelConfirm.setText("Необходимо выбрать элемент");
        } else if (Integer.parseInt(addCount.getText()) == 0) {
            labelConfirm.setText("Необходимо выбрать количество");
        } else {

            ProductsEntity addedItem = found.get(selectedIndex);
            int itemsCount = Integer.parseInt(addCount.getText());
            currentCheck.addItem(addedItem, itemsCount);
            found = null;
            stage.close();

        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCurrentCheck(ChecksEntity currentCheck) {
        this.currentCheck = currentCheck;
    }

    @FXML
    public void incAddCount() {
        if (found != null && items.getSelectionModel() != null) {
            labelConfirm.setText("");

            //TODO fix null pointer if nothing selected
            int selectedItemCount = found.get(items.getSelectionModel().getSelectedIndex()).getCount();

            try {
                int currentCount = Integer.parseInt(addCount.getText());
                if ((++currentCount) > selectedItemCount) throw new NumberFormatException();
                addCount.setText(currentCount + "");
            } catch (NumberFormatException e) {
                labelConfirm.setText("Неверное количество");
            }
        }
    }

    @FXML
    public void decAddCount() {
        if (found != null) {
            labelConfirm.setText("");

            try {
                int currentCount = Integer.parseInt(addCount.getText());
                if (currentCount == 0) throw new NumberFormatException();
                addCount.setText((--currentCount) + "");
            } catch (NumberFormatException e) {
                labelConfirm.setText("Неверное количество");
            }
        }
    }


}
