package com.projects.taxicorporation.client;

import javafx.scene.control.*;
import javafx.fxml.FXML;

public class AddCarController {
    @FXML
    private TextField carModelField;
    @FXML
    private TextField yearOfProductionField;
    @FXML
    private ChoiceBox departmentChoiceBox;
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
        //todo przekazanie loginu zapisanego przy logowaniu do bazy do logouta
    }
    public void onEndAddCarButtonClicked() {
        boolean isDataValid = validateCarModel() && validateYearOfProduction() &&
                departmentChoiceBox.getValue() != null;
        if(isDataValid) {
            //TODO baza danych
        }
        else
            showAlert("Wprowadzone dane są nieprawidłowe!", Alert.AlertType.ERROR);
    }
    private boolean validateCarModel() {
        String carModel = carModelField.getText();
        return carModel.length() >= 5;
    }
    private boolean validateYearOfProduction() {
        boolean isValid;
        String yearOfProduction = yearOfProductionField.getText();
        try {
            Integer.parseInt(yearOfProduction);
            isValid = (yearOfProduction.length() == 4);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            isValid = false;
        }
        return isValid;
    }
    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Informacja");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
