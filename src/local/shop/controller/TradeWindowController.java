package local.shop.controller;


import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import local.shop.MainWindow;
import local.shop.exceptions.NotEnoughItemsOnStore;
import local.shop.model.entity.ChecksEntity;
import local.shop.model.entity.SolditemsEntity;

public class TradeWindowController {

    @FXML
    private TableView<SolditemsEntity> items;
    @FXML
    private TableColumn<SolditemsEntity, Integer> id;
    @FXML
    private TableColumn<SolditemsEntity, String> name;
    @FXML
    private TableColumn<SolditemsEntity, Integer> count;
    @FXML
    private TableColumn<SolditemsEntity, Float> price;
    @FXML
    private Label lSumm;
    @FXML
    private Label lPayed;
    @FXML
    private Label lBackSumm;
    @FXML
    private TextField inputField;
    @FXML
    private TextArea logWindow;
    @FXML
    private Label lUnpayd;
    @FXML
    private Label lPayErr;

    private float checkSumm;
    private float checkPayed;

    ChecksEntity activeCheck = new ChecksEntity();

    @FXML
    public void initialize() {

        id.setCellValueFactory(cellData -> ((ObservableValue)new SimpleIntegerProperty(cellData.getValue().getProduct().getId())));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduct().getName()));
        price.setCellValueFactory(cellData -> ((ObservableValue)new SimpleFloatProperty(cellData.getValue().getProduct().getPrice())));
        count.setCellValueFactory(cellData -> ((ObservableValue) new SimpleIntegerProperty(cellData.getValue().getCount())));

    }

    @FXML
    public void addItemToCheck() {
        MainWindow.getInstance().showSearchDialog(activeCheck);
        updateCheck();
        recalcCheckSumm();
    }

    @FXML
    public void delItem() {
        int selectedIndex = items.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            activeCheck.remove(selectedIndex);
            items.setItems(activeCheck.toObservableList());
        }
        recalcCheckSumm();

    }

    @FXML
    public void clearCheck() {
        activeCheck = new ChecksEntity();
        items.setItems(activeCheck.toObservableList());
        updateCheck();

        recalcCheckSumm();
    }

    @FXML
    public void doPay() {
        String input = inputField.getText();
        if (!input.isEmpty() && checkSumm > 0) {
            float summ = 0.0f;
            try {
                summ = Float.parseFloat(input);
                checkPayed += summ;
            } catch (NumberFormatException e) {
                logWindow.appendText("Указана неверная сумма\n");
            }
            recalcCheckSumm();
        }
        inputField.setText("");
        lPayErr.setText("");
    }

    @FXML
    public void processCheck() {
        recalcCheckSumm();
        if (activeCheck.getItems().size() == 0) {
            logWindow.appendText("Чек пуст\n");
            return;
        }
        if (checkPayed < checkSumm) {
            lPayErr.setText("Чек не оплачен");
            return;
        }

        try {
            if (CheckUtil.processCheck(activeCheck)) {

                logWindow.appendText("Чек №" + activeCheck.getId() + " внесен в базу.\n");
                activeCheck = new ChecksEntity();
                checkPayed = 0;
                updateCheck();
                recalcCheckSumm();
            } else {
                logWindow.appendText("Ошибка пробития чека\n");

            }
        } catch (NotEnoughItemsOnStore er) {
            logWindow.appendText(er.getErrMsg() + "\n");

        }


    }

    private void updateCheck() {
        items.setItems(activeCheck.toObservableList());
        items.refresh();
    }

    private void recalcCheckSumm() {
        if (items.getItems().size() > 0) {
            checkSumm = activeCheck.getTotal();
            lSumm.setText(String.format("%.2f", checkSumm));


            if (checkPayed >= 0) {
                lPayed.setText(checkPayed + "");
                if (checkSumm > checkPayed) {
                    lBackSumm.setText("0.0");
                    lUnpayd.setText(String.format("%.2f", (checkSumm - checkPayed)));
                } else {
                    lBackSumm.setText(String.format("%.2f", (checkPayed - checkSumm)));
                    lUnpayd.setText("0.0");
                }
            }
        } else {
            checkSumm = 0.0f;
            checkPayed = 0.0f;
            lUnpayd.setText("0.0");
            lBackSumm.setText("0.0");
            lPayed.setText("0.0");
            lSumm.setText("0.0");
        }

    }

    @FXML
    public void showSearchWindow() {
        MainWindow.getInstance().showStoredItemsView();
    }

    @FXML
    public void sceneChange() {
      //  MainWindow.getInstance().showAnotherScene();
    }

    @FXML
    public void clearLog() {
        logWindow.setText("");
    }

    @FXML
    public void editItemsMenu() {
        MainWindow.getInstance().showItemsEditorWindow();
    }
    @FXML
    public void showReclamationWindow() {
        MainWindow.getInstance().initModalWindow("CheckViewWindow.fxml","Выбор чека для возврата",MainWindow.getInstance().getMainStage());
    }
}


