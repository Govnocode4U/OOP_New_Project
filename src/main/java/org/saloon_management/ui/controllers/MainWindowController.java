package org.saloon_management.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
@FxmlView("/main-view.fxml")
public class MainWindowController implements Initializable {
    private final FxWeaver fxWeaver;

    @FXML
    public AnchorPane root;

    public MainWindowController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public AnchorPane getAnchorPane() {
        return root;
    }

}