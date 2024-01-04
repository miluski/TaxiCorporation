package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class AddDepartmentController implements Controller {
    @FXML
    private TextField departmentNameField;
    @FXML
    private TextField departmentCityField;
    @FXML
    private TextField departmentStreetField;
    @FXML
    private AnchorPane buttonsAnchorPane;
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

    @Override
    public AnchorPane getButtonsAnchorPane() {
        return this.buttonsAnchorPane;
    }

    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new AddDepartmentFactory();
        Form form = formFactory.createForm();
        form.start();
    }
}
