package com.banco.bancodopovo.jgi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowController {

    public static Scene setSceneInWindow(ActionEvent event, String pathToFxml ) throws IOException {

        int defaultWidth = 800;
        int defaultHeight = 584;
        int minWidth = 780;
        int minHeight = 560;
        int maxWidth = 840;
        int maxHeight = 624;

        Parent view = FXMLLoader.load(WindowController.class.getResource(pathToFxml));
        Scene sceneToShow = new Scene(view);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setWidth(defaultWidth);
        window.setHeight(defaultHeight);
        window.setMinWidth(minWidth);
        window.setMinHeight(minHeight);
        window.setMaxHeight(maxHeight);
        window.setMaxWidth(maxWidth);
        window.setScene(sceneToShow);
        window.show();
        return sceneToShow;
    }


}
