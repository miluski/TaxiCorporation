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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class ManageManagerControllerTest {

    private ManageManagerController manageManagerController;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageManager.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            manageManagerController = fxmlLoader.getController();
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void initialize_ShouldInitiateElements() {
        assertNotNull(manageManagerController.buttonsAnchorPane);
        assertNotNull(manageManagerController.managerChoiceBox);
        assertNotNull(manageManagerController.departmentChoiceBox);
    }
    @Test
    void fetchDepartmentsData_ShouldPopulateDepartmentChoiceBox(FxRobot robot) {

        manageManagerController.fetchDepartmentsData();

        ChoiceBox<String> departmentChoiceBox = robot.lookup("#departmentChoiceBox").queryAs(ChoiceBox.class);

        assertFalse(departmentChoiceBox.getItems().size() > 0);
    }

    @Stop
    private void stop() {
        manageManagerController = null;
    }
}
