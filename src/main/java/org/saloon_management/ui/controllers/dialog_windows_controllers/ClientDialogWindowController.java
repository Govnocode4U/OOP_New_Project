package org.saloon_management.ui.controllers.dialog_windows_controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.saloon_management.models.Client;
import org.saloon_management.services.impl.ClientServiceImpl;
import org.saloon_management.utils.error_dialog_window.ErrorDialogWindow;
import org.saloon_management.utils.validators.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@Component
@FxmlView("/tabs/dialog_windows/clients-dialog-window.fxml")
public class ClientDialogWindowController {
    private Stage stage;
//    private static final Logger logger = LoggerFactory.getLogger(ClientDialogWindowController.class);
    @Autowired
    ErrorDialogWindow errorDialogWindow;
    @Autowired
    private ClientValidator clientValidator;
    @Autowired
    private ClientServiceImpl clientService;

    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onSaveButtonClicked() {
        try {
            if (validateFields()) {
                Client client = new Client(
                        nameField.getText(),
                        phoneField.getText(),
                        emailField.getText()
                );

                Errors errors = new BeanPropertyBindingResult(client, "client");
                clientValidator.validate(client, errors);

                if (errors.hasErrors()) {
//                    logger.info(errors.toString());
                    errorDialogWindow.showErrorMessage("Клиент с таким именем уже существует.");
                } else {
                    clientService.add(client);
                    stage.close();
                }
            }
        } catch (Exception e) {
            errorDialogWindow.showErrorMessage("Произошла ошибка при сохранении клиента.");
        }
    }

    @FXML
    public void onCancelButtonClicked() {
        stage.close();
    }

    private boolean validateFields() {
        try {
            if (nameField.getText().isEmpty() || emailField.getText().isEmpty()) {
                errorDialogWindow.showEmptyFieldsError();
                return false;
            }

            if (!phoneField.getText().matches("\\+7\\d{10}")) {
                errorDialogWindow.showErrorMessage("Неправильный формат номера телефона. Используйте формат +7XXXXXXXXXX.");
                return false;
            }

            return true;
        } catch (Exception e) {
//            LOGGER.info(e);
            errorDialogWindow.showErrorMessage("Произошла ошибка при проверке полей.");
            return false;
        }
    }
}

