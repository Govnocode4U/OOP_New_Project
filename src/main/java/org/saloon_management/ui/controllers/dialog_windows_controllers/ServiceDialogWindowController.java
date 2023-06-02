package org.saloon_management.ui.controllers.dialog_windows_controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.saloon_management.models.ServiceModel;
import org.saloon_management.services.impl.ServiceServiceImpl;
import org.saloon_management.ui.controllers.tables_controllers.ServicesVboxController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@Component
@FxmlView("/tabs/dialog_windows/services-dialog-window.fxml")
public class ServiceDialogWindowController {
    private Stage stage;
    @Autowired
    ServicesVboxController servicesVboxController;

    @Autowired
    private ServiceServiceImpl serviceService;

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onSaveButtonClicked() {
        if (validateFields()) {
            serviceService.add(new ServiceModel(
                    nameField.getText(),
                    priceField.getText()
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
        String price = priceField.getText();

        if (price.charAt(0) == '-') {showErrorMessage("поле должно быть положительным числом");}

        if (name.isEmpty() || price.isEmpty()) {
            showErrorMessage("Пожалуйста, заполните все поля.");
            return false;
        }

        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) {
            showErrorMessage("Поле Цена должно содержать числовое значение.");
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

