package org.saloon_management.ui.controllers.dialog_windows_controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.saloon_management.models.Appointment;
import org.saloon_management.models.Master;
import org.saloon_management.ui.controllers.tables_controllers.MastersVboxController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@FxmlView("/tabs/dialog_windows/masters-table.fxml")
public class MastersToServiceController {
    private Stage stage;
    @FXML
    private TextField searchField;
    @FXML
    private TableColumn<LocalDateTime, String> date;

    @FXML
    private TableView<LocalDateTime> mastersServiceTable;

    @Autowired
    MastersVboxController mastersVboxController;

    private Master selectedMaster;
    private FilteredList<LocalDateTime> filteredDateEntities;

    public void setSelectedMaster(Master selectedMaster) {
        this.selectedMaster = selectedMaster;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initializeTable() {
        setupTableColumns();
        setupSearchField();
        fillTableWithData();
    }

    private void setupTableColumns() {
        date.setCellValueFactory(cellData -> {
            LocalDateTime dateEntity = cellData.getValue();
            if (dateEntity != null) {
                String dateText = dateEntity.toString(); // Замените на соответствующий метод получения текста даты
                return new SimpleStringProperty(dateText);
            } else {
                return new SimpleStringProperty("");
            }
        });
    }

    private void fillTableWithData() {
        List<Appointment> appointments = selectedMaster.getAppointments();
        List<LocalDateTime> appointmentDates = appointments.stream()
                .map(Appointment::getDateTime)
                .collect(Collectors.toList());

        filteredDateEntities = new FilteredList<>(FXCollections.observableArrayList(appointmentDates), p -> true);
        mastersServiceTable.setItems(filteredDateEntities);
    }


    private void setupSearchField() {
        filteredDateEntities = new FilteredList<>(FXCollections.observableArrayList());
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterSchedules(newValue));
    }

    private void filterSchedules(String searchText) {
        filteredDateEntities.setPredicate(dateEntity -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowerCaseSearchText = searchText.toLowerCase();
            return dateEntity.toString().toLowerCase().contains(lowerCaseSearchText);
        });
    }
}

