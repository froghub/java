package local.shop;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import local.shop.controller.ModalWinController;
import local.shop.model.entity.ChecksEntity;
import local.shop.controller.SearchItemController;
import local.shop.controller.ViewStoredItemsController;

import java.io.IOException;

public class MainWindow extends Application {
    private static MainWindow instance;

    private Stage mainStage;

    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    public void init(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setResizable(false);
        MainWindow.getInstance().setMainStage(primaryStage);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainWindow.class.getResource("view/TradeWindow.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void showSearchDialog(ChecksEntity currentCheck) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("view/SearchItem.fxml"));
            AnchorPane page = loader.load();

            Stage searchStage = new Stage();
            searchStage.setResizable(false);
            searchStage.setTitle("Поиск товара");
            searchStage.initModality(Modality.WINDOW_MODAL);
            searchStage.initOwner(mainStage);

            Scene scene = new Scene(page);
            searchStage.setScene(scene);


            SearchItemController controller = loader.getController();

            controller.setStage(searchStage);
            controller.setCurrentCheck(currentCheck);

            searchStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStoredItemsView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("view/ViewStoredItems.fxml"));
            AnchorPane page = loader.load();
            Stage searchStage = new Stage();
            searchStage.setResizable(false);
            searchStage.setTitle("Поиск товара");
            searchStage.initModality(Modality.WINDOW_MODAL);

            searchStage.initOwner(mainStage);
            Scene scene = new Scene(page);
            searchStage.setScene(scene);
            ViewStoredItemsController controller = loader.getController();
            controller.setStage(searchStage);
            searchStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showItemsEditorWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("view/StoredItemsEdit.fxml"));
            AnchorPane page = loader.load();
            Stage searchStage = new Stage();
            searchStage.setResizable(false);
            searchStage.setTitle("Редактирование");
            searchStage.initModality(Modality.WINDOW_MODAL);

            searchStage.initOwner(mainStage);
            Scene scene = new Scene(page);
            searchStage.setScene(scene);

            searchStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ModalWinController initModalWindow(String resourceName, String windowTitle,Stage owner) {
        ModalWinController controller = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("view/" + resourceName)); //ViewStoredItems.fxml
            AnchorPane pane = loader.load();
            Stage modalStage = new Stage();
            modalStage.setResizable(false);
            modalStage.setTitle(windowTitle);
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(owner);
            Scene scene = new Scene(pane);
            modalStage.setScene(scene);
            controller = loader.getController();
            controller.setStage(modalStage);
            modalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }
   /* public void showAnotherScene() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("view/TabView.fxml"));

            Stage searchStage = new Stage();
            searchStage.setResizable(false);
            searchStage.setTitle("Custom scene");
            //searchStage.initModality(Modality.WINDOW_MODAL);
            //searchStage.initOwner(mainStage);
            Scene scene = new Scene(loader.load());
            mainStage.setScene(scene);
            //searchStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // return false;
        }
    }*/


}
