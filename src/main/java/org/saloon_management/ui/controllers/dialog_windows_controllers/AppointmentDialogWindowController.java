package org.saloon_management.ui.controllers.dialog_windows_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.saloon_management.models.*;
import org.saloon_management.services.impl.*;
import org.saloon_management.ui.controllers.tables_controllers.AppointmentVboxController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@Component
@FxmlView("/tabs/dialog_windows/appointments-dialog-window.fxml")
public class AppointmentDialogWindowController {
    private Stage stage;
    @Autowired
    AppointmentVboxController appointmentVboxController;
    @Autowired
    private AppointmentServiceImpl appointmentService;
    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private MasterServiceImpl masterService;
    @Autowired
    private ServiceServiceImpl serviceService;

    @FXML
    private ChoiceBox<String> clientField;
    @FXML
    private ChoiceBox<String> serviceField;
    @FXML
    private ChoiceBox<String> masterField;
    @FXML
    private TextField timeField;

    @FXML
    private DatePicker datePicker;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onSaveButtonClicked() throws RuntimeException {
        if (validateFields()) {
            LocalDate selectedDate = datePicker.getValue();
            LocalTime selectedTime = LocalTime.parse(timeField.getText());

            LocalDateTime dateTime = LocalDateTime.of(selectedDate, selectedTime)
                    .withSecond(0);

            appointmentService.add(new Appointment(
                    clientService.getOne(clientField.getValue()),
                    masterService.getOne(masterField.getValue()),
                    serviceService.getOne(serviceField.getValue()),
                    dateTime
            ));

            Master master = masterService.getOne(masterField.getValue());

            ServiceModel selectedService = serviceService.getOne(serviceField.getValue());
            master.addServiceToList(selectedService);
            masterService.add(master);

            stage.close();
        }
    }

    @FXML
    public void initialize() {
        for (Client client : clientService.getAll()) clientField.getItems().add(client.getFullName());
        for (Master master : masterService.getAll()) masterField.getItems().add(master.getFullName());
        for (ServiceModel serviceModel : serviceService.getAll()) serviceField.getItems().add(serviceModel.getName());
    }

    @FXML
    public void onCancelButtonClicked() {
        stage.close();
    }

    private boolean validateFields() {
        String time = timeField.getText();
        LocalDate date = datePicker.getValue();

        if (time.isEmpty() || date == null) {
            showErrorMessage("Пожалуйста, заполните все поля.");
            return false;
        }

        if (!time.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            showErrorMessage("Неправильный формат времени. Используйте формат hh:mm.");
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

