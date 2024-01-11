package client;

import com.projects.taxicorporation.client.AddCarController;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddCarControllerTest {

    @Test
    void validateCarModel_ValidCarModel_ReturnsTrue() {
        AddCarController addCarController = new AddCarController();
        TextField carModelField = new TextField("ValidCarModel");

        addCarController.carModelField = carModelField;

        assertTrue(addCarController.validateCarModel());
    }

    @Test
    void validateCarModel_InvalidCarModel_ReturnsFalse() {
        AddCarController addCarController = new AddCarController();
        TextField carModelField = new TextField("Short");

        addCarController.carModelField = carModelField;

        assertFalse(addCarController.validateCarModel());
    }

    @Test
    void validateYearOfProduction_ValidYear_ReturnsTrue() {
        AddCarController addCarController = new AddCarController();
        TextField yearOfProductionField = new TextField("2022");

        addCarController.yearOfProductionField = yearOfProductionField;

        assertTrue(addCarController.validateYearOfProduction());
    }

    @Test
    void validateYearOfProduction_InvalidYear_ReturnsFalse() {
        AddCarController addCarController = new AddCarController();
        TextField yearOfProductionField = new TextField("InvalidYear");

        addCarController.yearOfProductionField = yearOfProductionField;

        assertFalse(addCarController.validateYearOfProduction());
    }

    @Test
    void onEndAddCarButtonClicked_ValidData_SuccessfulCommunication() {
        // Set up the controller and mock dependencies
        AddCarController addCarController = new AddCarController();
        addCarController.carModelField = new TextField("ValidCarModel");
        addCarController.yearOfProductionField = new TextField("2022");
        addCarController.departmentChoiceBox = new ChoiceBox<>();

        // Mock the AlertDialog
        AlertDialog alertDialogMock = mock(AlertDialog.class);
        addCarController.alertDialog = alertDialogMock;

        // Mock the communicateWithServer method
        AddCarController spyController = spy(addCarController);
        doNothing().when(spyController).communicateWithServer(anyString());

        // Call the method to be tested
        spyController.onEndAddCarButtonClicked();

        // Verify that the communicateWithServer method was called
        verify(spyController, times(1)).communicateWithServer("AddCar");

        // Verify that AlertDialog.getInstance() was called
        verify(alertDialogMock, times(1)).setParametersAndShow(anyString(), eq(Alert.AlertType.INFORMATION));
    }

    @Test
    void onEndAddCarButtonClicked_InvalidData_ShowErrorAlert() {
        // Set up the controller with invalid data
        AddCarController addCarController = new AddCarController();
        addCarController.carModelField = new TextField("Invalid");
        addCarController.yearOfProductionField = new TextField("Invalid");
        addCarController.departmentChoiceBox = new ChoiceBox<>();

        // Mock the AlertDialog
        AlertDialog alertDialogMock = mock(AlertDialog.class);
        addCarController.alertDialog = alertDialogMock;

        // Call the method to be tested
        addCarController.onEndAddCarButtonClicked();

        // Verify that AlertDialog.getInstance() was called with an error message
        verify(alertDialogMock, times(1)).setParametersAndShow(eq("Wprowadzone dane są nieprawidłowe!"), eq(Alert.AlertType.ERROR));

        // Verify that communicateWithServer method was not called
        verify(addCarController, never()).communicateWithServer(anyString());
    }
}
