package org.saloon_management.utils.error_dialog_window;

import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;

@Component
public class ErrorDialogWindow {

    public void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public boolean showEmptyFieldsError() {
        this.showErrorMessage("Пожалуйста, заполните все поля.");
        return false;
    }

}
