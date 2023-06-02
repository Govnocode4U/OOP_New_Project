package org.saloon_management.ui.controllers.tables_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import net.rgielen.fxweaver.core.FxmlView;
import org.saloon_management.models.Appointment;
import org.saloon_management.models.Master;
import org.saloon_management.services.impl.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
@FxmlView("/tabs/vboxs/home-vbox-table.fxml")
public class HomeVboxController implements Initializable {
    @FXML
    private DatePicker fromDatepicker;

    @FXML
    private DatePicker toDatepicker;
    @FXML
    private Button calculateButton;

    @FXML
    private TextArea totalSum;
    @FXML
    private TextArea mostPrib;
    @FXML
    private TextArea mostVostr;
    @FXML
    private TextArea countClients;

    @Autowired
    private AppointmentServiceImpl appointmentService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        totalSum.setText("0");
        mostPrib.setText("none");
        mostVostr.setText("none");
        countClients.setText("0");

        totalSum.setEditable(false);
        mostPrib.setEditable(false);
        mostVostr.setEditable(false);
        countClients.setEditable(false);
    }

    @FXML
    public void onCalculateButtonClicked() {
        LocalDate fromDate = fromDatepicker.getValue();
        LocalDate toDate = toDatepicker.getValue();

        if (fromDate != null && toDate != null) {
            List<Appointment> appointments = appointmentService.getAppointmentsBetweenDates(fromDate, toDate);



            BigDecimal totalProfit = appointments.stream()
                    .map(Appointment::getServicePrice)
                    .map(BigDecimal::new) // Преобразование строки в BigDecimal
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            totalSum.setText(totalProfit.toString());



            // Find the most profitable master
            Map<Master, BigDecimal> masterProfitMap = appointments.stream()
                    .collect(Collectors.groupingBy(Appointment::getMaster,
                            Collectors.reducing(BigDecimal.ZERO,
                                    appointment -> new BigDecimal(appointment.getServicePrice()),
                                    BigDecimal::add)));
            Master mostProfitableMaster = masterProfitMap.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);
            mostPrib.setText(mostProfitableMaster != null ? mostProfitableMaster.getFullName() : "none");


            // Find the most in-demand master
            Map<Master, Long> masterCountMap = appointments.stream()
                    .collect(Collectors.groupingBy(Appointment::getMaster, Collectors.counting()));
            Master mostInDemandMaster = masterCountMap.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);
            mostVostr.setText(mostInDemandMaster != null ? mostInDemandMaster.getFullName() : "none");

            long clientCount = appointments.stream()
                    .map(Appointment::getClient)
                    .count();
            countClients.setText(String.valueOf(clientCount));

        }
    }

}


