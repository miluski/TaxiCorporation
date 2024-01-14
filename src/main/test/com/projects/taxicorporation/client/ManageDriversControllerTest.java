package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.Stop;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
class ManageDriversControllerTest {

    private ManageDriversController manageDriversController;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageDrivers.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            manageDriversController = fxmlLoader.getController();
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void initialize_ShouldInitiateElements() {
        assertNotNull(manageDriversController.buttonsAnchorPane);
        assertNotNull(manageDriversController.driverChoiceBox);
        assertNotNull(manageDriversController.departmentChoiceBox);
    }

    @Test
    void fetchDepartmentsData_ShouldPopulateDepartmentChoiceBox(FxRobot robot) {
        manageDriversController.fetchDepartmentsData();

        robot.lookup("#departmentChoiceBox").queryAs(ChoiceBox.class);
    }

    @Test
    void fetchDriversData_ShouldPopulateDriverChoiceBox(FxRobot robot) {

        manageDriversController.fetchDriversData();

        robot.lookup("#driverChoiceBox").queryAs(ChoiceBox.class);
    }

    @Stop
    private void stop() {
        manageDriversController = null;
    }
}
