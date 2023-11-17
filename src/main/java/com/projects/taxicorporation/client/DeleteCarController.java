package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DeleteCarController {
    @FXML
    private ChoiceBox chooseDepartmentChoiceBox;
    @FXML
    private ChoiceBox chooseCarChoiceBox;
    public void onAddCarButtonClicked() throws Exception {
        FormFactory formFactory = new AddCarFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onAddMenagerButtonClicked() throws Exception {
        FormFactory formFactory = new AddManagerFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onDeleteMenagerButtonClicked() throws Exception {
        FormFactory formFactory = new DeleteManagerFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onMenageMenagersButtonClicked() throws Exception {
        FormFactory formFactory = new ManageManagerFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onAddDepartmentButtonClicked() throws Exception {
        FormFactory formFactory = new AddDepartmentFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onMenageDepartmentsButtonClicked() throws Exception {
        FormFactory formFactory = new RenameOrDeleteDepartmentFactory();
        Form form = formFactory.createForm();
        form.start();
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
