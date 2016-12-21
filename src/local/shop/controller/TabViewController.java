package local.shop.controller;


import javafx.fxml.FXML;
import local.shop.MainWindow;
import java.io.IOException;


public class TabViewController {

    @FXML
    public void getSceneBack() {
        try {
            MainWindow.getInstance().start(MainWindow.getInstance().getMainStage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
