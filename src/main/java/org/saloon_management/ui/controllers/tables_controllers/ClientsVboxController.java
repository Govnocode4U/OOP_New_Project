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
import org.saloon_management.models.Client;
import org.saloon_management.services.impl.AppointmentServiceImpl;
import org.saloon_management.services.impl.ClientServiceImpl;
import org.saloon_management.ui.controllers.dialog_windows_controllers.ClientDialogWindowController;
import org.saloon_management.ui.controllers.edit_dialog_window_controllers.EditClientDialogWindowController;
import org.saloon_management.utils.pdf.ClientPdfGenerator;
import org.saloon_management.utils.xml.ClientXmlExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


@Component
@FxmlView("/tabs/vboxs/clients-vbox-table.fxml")
public class ClientsVboxController implements Initializable {
    private final FxWeaver fxWeaver;
    @FXML
    public Button addClient;
    @FXML
    private TableColumn<Client, String> idColumn;
    @FXML
    private TableColumn<Client, String> fullNameColumn;
    @FXML
    private TableColumn<Client, String> phoneColumn;
    @FXML
    private TableColumn<Client, String> emailColumn;
    @FXML
    private TableView<Client> clientsTable;

    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private AppointmentServiceImpl appointmentService;

    public ClientsVboxController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    public void initializeTable() {
        clientsTable.getItems().addAll(clientService.getAll());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();
    }

    public void refreshTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    public void onAddCLientButtonCLicked() {
        ClientDialogWindowController dialogController = fxWeaver.loadController(ClientDialogWindowController.class);

        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(fxWeaver.loadView(ClientDialogWindowController.class), 500, 300));
        dialogController.setStage(dialogStage);

        dialogStage.showAndWait();
    }

    @FXML
    public void onGetFromXmlButtonClicked() {

        File outputFile = new File("src/main/resources/xml/clients.xml");

        ClientXmlExporter.exportClientsToXml(clientService.getAll(), outputFile);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("load xml");
        alert.setHeaderText(null);
        alert.setContentText("Файл успешно обновился");
        alert.showAndWait();
    }


    @FXML
    public void onDeleteCLientButtonCLicked() {
        Client selectedClient = clientsTable.getSelectionModel().getSelectedItem();

        clientService.delete(selectedClient);

        refreshTable();
    }

    @FXML
    public void onEditClientButtonClicked() {
        Client selectedClient = clientsTable.getSelectionModel().getSelectedItem();
        EditClientDialogWindowController dialogController = fxWeaver.loadController(EditClientDialogWindowController.class);

        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(fxWeaver.loadView(EditClientDialogWindowController.class), 500, 300));
        dialogController.setStage(dialogStage);
        dialogController.setSelectedClient(selectedClient);
        dialogController.initializeFields();

        dialogStage.showAndWait();

    }

    @FXML
    public void onGeneratePdfButtonClicked() {
        File outputFile = new File("src/main/resources/pdf/clients.pdf");
        ClientPdfGenerator.generateClientsPdf(clientService.getAll(), outputFile);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("load pdf");
        alert.setHeaderText(null);
        alert.setContentText("Файл успешно обновился");
        alert.showAndWait();
    }


}

