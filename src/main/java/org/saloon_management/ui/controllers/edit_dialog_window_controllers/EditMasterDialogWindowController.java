package org.saloon_management.ui.controllers.edit_dialog_window_controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.saloon_management.models.Master;
import org.saloon_management.models.Master;
import org.saloon_management.services.impl.MasterServiceImpl;
import org.saloon_management.services.impl.MasterServiceImpl;
import org.saloon_management.ui.controllers.tables_controllers.MastersVboxController;
import org.saloon_management.ui.controllers.tables_controllers.MastersVboxController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/tabs/edit_dialog_windows/masters-dialog-window.fxml")
public class EditMasterDialogWindowController {
    private Stage stage;
    @Autowired
    MastersVboxController mastersVboxController;

    @Autowired
    private MasterServiceImpl masterService;
    private Master selectedMaster;
    @FXML
    private TextField nameField;
    @FXML
    private TextField specializationField;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSelectedMaster(Master selectedMaster) {
        this.selectedMaster = selectedMaster;
    }

    public void initializeFields() {
        nameField.setText(selectedMaster.getFullName());
        specializationField.setText(selectedMaster.getSpecialization());
    }

    @FXML
    public void onSaveButtonClicked() {
        selectedMaster.setFullName(nameField.getText());
        selectedMaster.setSpecialization(specializationField.getText());

        masterService.add(selectedMaster);
        stage.close();
    }

    @FXML
    public void onCancelButtonClicked() {
        stage.close();
    }
}

