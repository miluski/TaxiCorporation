package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddDepartmentController {
    @FXML
    private TextField departmentNameField;
    @FXML
    private TextField departmentCityField;
    @FXML
    private TextField departmentStreetField;
    @FXML
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
    public void onAddCarButtonClicked() throws Exception {
        FormFactory formFactory = new AddCarFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onMenageDepartmentsButtonClicked() throws Exception {
        FormFactory formFactory = new RenameOrDeleteDepartmentFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onDeleteCarButtonClicked() throws Exception {
        FormFactory formFactory = new DeleteCarFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
    }
    public void onEndAddDepartmentButtonClicked() {
    }
    public void onEditOrDeleteDepartmentButtonClicked() {
    }
    private boolean validateDepartmentName() {
        String departmentName = departmentNameField.getText();
        try {
            Integer.parseInt(departmentName);
        }
        catch (Exception e) {
            return departmentName.length() >= 4;
        }
        return false;
    }
    private boolean validateDepartmentCity() {
        String departmentCity = departmentCityField.getText();
        try {
            Integer.parseInt(departmentCity);
        }
        catch (Exception e) {
            return departmentCity.length() >= 6;
        }
        return false;
    }
    private boolean validateDepartmentStreet() {
        String departmentStreet = departmentStreetField.getText();
        try {
            Integer.parseInt(departmentStreet);
        }
        catch (Exception e) {
            return departmentStreet.length() >= 5;
        }
        return false;
    }
}
