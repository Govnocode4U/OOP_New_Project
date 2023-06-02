package org.saloon_management.ui.controllers.tables_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.saloon_management.models.Master;
import org.saloon_management.models.ServiceModel;
import org.saloon_management.services.impl.ServiceServiceImpl;
import org.saloon_management.ui.controllers.dialog_windows_controllers.ServiceDialogWindowController;
import org.saloon_management.ui.controllers.edit_dialog_window_controllers.EditMasterDialogWindowController;
import org.saloon_management.ui.controllers.edit_dialog_window_controllers.EditServiceDialogWindowController;
import org.saloon_management.utils.pdf.ClientPdfGenerator;
import org.saloon_management.utils.pdf.ServicePdfGenerator;
import org.saloon_management.utils.xml.MasterXmlExporter;
import org.saloon_management.utils.xml.ServiceXmlExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


@Component
@FxmlView("/tabs/vboxs/services-vbox-table.fxml")
public class ServicesVboxController implements Initializable {
    private final FxWeaver fxWeaver;
    @FXML
    public Button addService;
    @FXML
    private TableColumn<ServiceModel, String> idColumn;
    @FXML
    private TableColumn<ServiceModel, String> nameColumn;
    @FXML
    private TableColumn<ServiceModel, String> priceColumn;
    @FXML
    private TableView<ServiceModel> servicesTable;
    @Autowired
    private ServiceServiceImpl serviceService;

    public ServicesVboxController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    public void initializeTable() {
        servicesTable.getItems().addAll(serviceService.getAll());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();
    }

    public void refreshTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    public void onAddServiceButtonCLicked() {
        ServiceDialogWindowController dialogController = fxWeaver.loadController(ServiceDialogWindowController.class);

        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(fxWeaver.loadView(ServiceDialogWindowController.class), 500, 300));
        dialogController.setStage(dialogStage);

        dialogStage.showAndWait();
    }

    @FXML
    public void onGetFromXmlButtonCLicked() {
        File outputFile = new File("src/main/resources/xml/services.xml");

        ServiceXmlExporter.exportServicesToXml(serviceService.getAll(), outputFile);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("load xml");
        alert.setHeaderText(null);
        alert.setContentText("Файл успешно обновился");
        alert.showAndWait();
    }

    @FXML
    public void onDeleteServiceButtonCLicked() {
        ServiceModel selectedService = servicesTable.getSelectionModel().getSelectedItem();

        selectedService.removeServiceAssociations();
        serviceService.delete(selectedService.getId());

        refreshTable();
    }

    @FXML
    public void onEditServiceButtonClicked() {
        // Получаем выбранную строку
        ServiceModel selectedService = servicesTable.getSelectionModel().getSelectedItem();
        EditServiceDialogWindowController dialogController = fxWeaver.loadController(EditServiceDialogWindowController.class);

        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(fxWeaver.loadView(EditServiceDialogWindowController.class), 500, 300));
        dialogController.setStage(dialogStage);
        dialogController.setSelectedServiceModel(selectedService);
        dialogController.initializeFields();

        dialogStage.showAndWait();

    }

    @FXML
    public void onGeneratePdfButtonClicked() {
        File outputFile = new File("src/main/resources/pdf/services.pdf");
        ServicePdfGenerator.generateServicesPdf(serviceService.getAll(), outputFile);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("load pdf");
        alert.setHeaderText(null);
        alert.setContentText("Файл успешно обновился");
        alert.showAndWait();
    }
}

