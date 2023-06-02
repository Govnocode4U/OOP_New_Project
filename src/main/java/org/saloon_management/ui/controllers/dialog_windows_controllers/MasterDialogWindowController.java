package org.saloon_management.ui.controllers.dialog_windows_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.saloon_management.models.Master;
import org.saloon_management.services.impl.MasterServiceImpl;
import org.saloon_management.ui.controllers.tables_controllers.MastersVboxController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/tabs/dialog_windows/masters-dialog-window.fxml")
public class MasterDialogWindowController {
    private Stage stage;
    @Autowired
    private MastersVboxController mastersVboxController;

    @Autowired
    private MasterServiceImpl masterService;

    @FXML
    private TextField nameField;
    @FXML
    private TextField specializationField;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onSaveButtonClicked() {
        if (validateFields()) {
            masterService.add(new Master(
                    nameField.getText(),
                    specializationField.getText()
            ));
            stage.close();
        }
    }

    @FXML
    public void onCancelButtonClicked() {
        stage.close();
    }

    private boolean validateFields() {
        String name = nameField.getText();
        String specialization = specializationField.getText();

        if (name.isEmpty() || specialization.isEmpty()) {
            showErrorMessage("Пожалуйста, заполните все поля.");
            return false;
        }

        return true;
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

