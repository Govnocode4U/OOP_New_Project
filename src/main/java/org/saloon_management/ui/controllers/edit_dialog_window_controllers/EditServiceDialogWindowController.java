package org.saloon_management.ui.controllers.edit_dialog_window_controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.saloon_management.models.ServiceModel;
import org.saloon_management.services.impl.ServiceServiceImpl;
import org.saloon_management.ui.controllers.tables_controllers.ServicesVboxController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/tabs/edit_dialog_windows/services-dialog-window.fxml")
public class EditServiceDialogWindowController {
    private Stage stage;
    @Autowired
    ServicesVboxController servicesVboxController;

    @Autowired
    private ServiceServiceImpl serviceService;
    private ServiceModel selectedServiceModel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSelectedServiceModel(ServiceModel selectedServiceModel) {
        this.selectedServiceModel = selectedServiceModel;
    }

    public void initializeFields() {
        nameField.setText(selectedServiceModel.getName());
        priceField.setText(selectedServiceModel.getPrice());
    }

    @FXML
    public void onSaveButtonClicked() {
        selectedServiceModel.setName(nameField.getText());
        selectedServiceModel.setPrice(priceField.getText());

        serviceService.add(selectedServiceModel);
        stage.close();
    }

    @FXML
    public void onCancelButtonClicked() {
        stage.close();
    }
}

