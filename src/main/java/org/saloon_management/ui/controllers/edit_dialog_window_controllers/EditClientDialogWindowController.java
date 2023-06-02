package org.saloon_management.ui.controllers.edit_dialog_window_controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.saloon_management.models.Client;
import org.saloon_management.services.impl.ClientServiceImpl;
import org.saloon_management.ui.controllers.tables_controllers.ClientsVboxController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/tabs/edit_dialog_windows/clients-dialog-window.fxml")
public class EditClientDialogWindowController {
    private Stage stage;
    @Autowired
    ClientsVboxController clientsVboxController;

    @Autowired
    private ClientServiceImpl clientService;
    private Client selectedClient;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public void initializeFields() {
        nameField.setText(selectedClient.getFullName());
        phoneField.setText(selectedClient.getPhone());
        emailField.setText(selectedClient.getEmail());
    }

    @FXML
    public void onSaveButtonClicked() {
        selectedClient.setFullName(nameField.getText());
        selectedClient.setPhone(phoneField.getText());
        selectedClient.setEmail(emailField.getText());

        clientService.add(selectedClient);
        stage.close();
    }

    @FXML
    public void onCancelButtonClicked() {
        stage.close();
    }
}

