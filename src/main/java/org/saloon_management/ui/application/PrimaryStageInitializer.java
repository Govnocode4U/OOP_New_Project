package org.saloon_management.ui.application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.saloon_management.ui.controllers.MainWindowController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {

    private FxWeaver fxWeaver;

    @Autowired
    public void primaryStageInitializer(FxWeaver fxWeaver) { //(1)
        this.fxWeaver = fxWeaver;
    }

    public PrimaryStageInitializer(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) { //(2)
        Stage stage = event.getStage();
        Scene scene = new Scene(fxWeaver.loadView(MainWindowController.class), 1000, 601);
        stage.setScene(scene);
        stage.show();
    }
}
