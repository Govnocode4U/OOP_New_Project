package org.saloon_management.ui.controllers.tables_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.saloon_management.models.Master;
import org.saloon_management.services.impl.MasterServiceImpl;
import org.saloon_management.ui.controllers.dialog_windows_controllers.MasterDialogWindowController;
import org.saloon_management.ui.controllers.dialog_windows_controllers.MastersToServiceController;
import org.saloon_management.ui.controllers.edit_dialog_window_controllers.EditMasterDialogWindowController;
import org.saloon_management.utils.pdf.MasterPdfGenerator;
import org.saloon_management.utils.xml.MasterXmlExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/tabs/vboxs/masters-vbox-table.fxml")
public class MastersVboxController implements Initializable {
    private final FxWeaver fxWeaver;
    @FXML
    private TableColumn<Master, String> idColumn;
    @FXML
    private TableColumn<Master, String> fullNameColumn;
    @FXML
    private TableColumn<Master, String> specializationColumn;
    @FXML
    private TableView<Master> mastersTable;

    @Autowired
    private MasterServiceImpl masterService;

    public void initializeTable() {
        mastersTable.getItems().addAll(masterService.getAll());
    }

    public MastersVboxController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        specializationColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));
    }

    @FXML
    public void onAddMasterButtonCLicked() {
        MasterDialogWindowController dialogController = fxWeaver.loadController(MasterDialogWindowController.class);

        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(fxWeaver.loadView(MasterDialogWindowController.class), 500, 300));
        dialogController.setStage(dialogStage);

        dialogStage.showAndWait();
    }

    @FXML
    public void onGetFromXmlButtonCLicked() {
        File outputFile = new File("src/main/resources/xml/masters.xml");

        MasterXmlExporter.exportMastersToXml(masterService.getAll(), outputFile);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("load xml");
        alert.setHeaderText(null);
        alert.setContentText("Файл успешно обновился");
        alert.showAndWait();
    }

    @FXML
    public void onDeleteMasterButtonCLicked() {
        Master selectedMaster = mastersTable.getSelectionModel().getSelectedItem();

        masterService.delete(selectedMaster.getId());
    }

    @FXML
    public void onShowServicesButtonClicked() {
        Master selectedMaster = mastersTable.getSelectionModel().getSelectedItem();
        MastersToServiceController dialogController = fxWeaver.loadController(MastersToServiceController.class);

        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(fxWeaver.loadView(MastersToServiceController.class), 500, 300));

        dialogController.setStage(dialogStage);
        dialogController.setSelectedMaster(selectedMaster);
        dialogController.initializeTable();

        dialogStage.showAndWait();
    }


    @FXML
    public void onEditMasterButtonClicked() {
        Master selectedMaster = mastersTable.getSelectionModel().getSelectedItem();
        EditMasterDialogWindowController dialogController = fxWeaver.loadController(EditMasterDialogWindowController.class);

        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(fxWeaver.loadView(EditMasterDialogWindowController.class), 500, 300));
        dialogController.setStage(dialogStage);
        dialogController.setSelectedMaster(selectedMaster);
        dialogController.initializeFields();

        dialogStage.showAndWait();

    }

    @FXML
    public void onGeneratePdfButtonClicked() {
        File outputFile = new File("src/main/resources/pdf/masters.pdf");
        MasterPdfGenerator.generateMastersPdf(masterService.getAll(), outputFile);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("load pdf");
        alert.setHeaderText(null);
        alert.setContentText("Файл успешно обновился");
        alert.showAndWait();
    }
}
