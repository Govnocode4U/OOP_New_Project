package org.saloon_management.ui.controllers.button_controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.saloon_management.ui.controllers.MainWindowController;
import org.saloon_management.ui.controllers.tables_controllers.ClientsVboxController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@FxmlView("/tabs/buttons/clients-button.fxml")
public class ClientsButtonController {
    @Autowired
    private MainWindowController mainWindowController;
    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    public void onClientsButtonClicked() throws IOException {
        FXMLLoader loaderTable = new FXMLLoader(getClass().getResource("/tabs/vboxs/clients-vbox-table.fxml"));
        loaderTable.setControllerFactory(applicationContext::getBean);
        VBox content = loaderTable.load();

        BorderPane borderPane = (BorderPane) mainWindowController.getAnchorPane().getChildren().get(0);
        borderPane.setCenter(content);

        ClientsVboxController tableController = loaderTable.getController();
        tableController.initializeTable();
    }
}




