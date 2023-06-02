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
import org.saloon_management.models.Appointment;
import org.saloon_management.services.impl.AppointmentServiceImpl;
import org.saloon_management.ui.controllers.dialog_windows_controllers.AppointmentDialogWindowController;
import org.saloon_management.utils.pdf.AppointmentPdfGenerator;
import org.saloon_management.utils.xml.AppointmentXmlExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


@Component
@FxmlView("/tabs/vboxs/appointment-vbox-table.fxml")
public class AppointmentVboxController implements Initializable {
    private final FxWeaver fxWeaver;
    @FXML
    public Button addAppointment;
    @FXML
    private TableColumn<Appointment, String> idColumn;
    @FXML
    private TableColumn<Appointment, String> clientName;
    @FXML
    private TableColumn<Appointment, String> idMasterColumn;
    @FXML
    private TableColumn<Appointment, String> serviceName;
    @FXML
    private TableColumn<Appointment, LocalDateTime> idDateColumn;
    @FXML
    private TableView<Appointment> appointmentsTable;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    public AppointmentVboxController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    public void initializeTable() {
        appointmentsTable.getItems().addAll(appointmentService.getAll());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();
    }

    public void refreshTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        idMasterColumn.setCellValueFactory(new PropertyValueFactory<>("masterName"));
        serviceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        idDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
    }


    @FXML
    public void onAddAppointmentButtonCLicked() {
        AppointmentDialogWindowController dialogController = fxWeaver.loadController(AppointmentDialogWindowController.class);

        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(fxWeaver.loadView(AppointmentDialogWindowController.class), 522, 387));
        dialogController.setStage(dialogStage);

        dialogStage.showAndWait();
    }

    @FXML
    public void onGetFromXmlButtonCLicked() {
        File outputFile = new File("src/main/resources/xml/appointments.xml");

        AppointmentXmlExporter.exportAppointmentsToXml(appointmentService.getAll(), outputFile);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("load xml");
        alert.setHeaderText(null);
        alert.setContentText("Файл успешно обновился");
        alert.showAndWait();
    }

    @FXML
    public void onDeleteAppointmentButtonCLicked() {
        Appointment selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();

        appointmentService.delete(selectedAppointment.getId());

        refreshTable();
    }

    @FXML
    public void onGeneratePdfButtonClicked() {
        File outputFile = new File("src/main/resources/pdf/appointments.pdf");
        AppointmentPdfGenerator.generateAppointmentssPdf(appointmentService.getAll(), outputFile);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("load pdf");
        alert.setHeaderText(null);
        alert.setContentText("Файл успешно обновился");
        alert.showAndWait();
    }
}

