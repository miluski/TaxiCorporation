package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DeleteCarController {
    @FXML
    private ChoiceBox chooseDepartmentChoiceBox;
    @FXML
    private ChoiceBox chooseCarChoiceBox;
    public void onAddMenagerButtonClicked() {
    }

    public void onDeleteMenagerButtonClicked() {
    }

    public void onMenageMenagersButtonClicked() {
    }

    public void onAddDepartmentButtonClicked() {
    }

    public void onMenageDepartmentsButtonClicked() {
    }

    public void onAddCarButtonClicked() {
    }

    public void onDeleteCarButtonClicked() {
    }

    public void onLogoutButtonClicked() {
    }

    public void onEndDeleteCarButtonClicked() {
        boolean isDataValid = chooseCarChoiceBox.getValue() != null && chooseDepartmentChoiceBox.getValue() != null;
        if(isDataValid) {
            //todo baza danych
        }
        else
            showAlert("Nie wybrano samochodu/oddziału!", Alert.AlertType.ERROR);
    }
    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Informacja");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
