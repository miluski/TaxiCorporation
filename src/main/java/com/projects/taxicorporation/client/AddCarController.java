package com.projects.taxicorporation.client;

import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class AddCarController implements Controller {
    @FXML
    private TextField carModelField;
    @FXML
    private TextField yearOfProductionField;
    @FXML
    private ChoiceBox departmentChoiceBox;
    @FXML
    private AnchorPane buttonsAnchorPane;
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
    public void onDeleteCarButtonClicked() throws Exception {
        FormFactory formFactory = new DeleteCarFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() throws Exception  {
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }
    public void onEndAddCarButtonClicked() {
        boolean isDataValid = validateCarModel() && validateYearOfProduction() &&
                departmentChoiceBox.getValue() != null;
        if(isDataValid) {
            //TODO baza danych
        }
        else
            AlertDialog.getInstance().setParametersAndShow("Wprowadzone dane są nieprawidłowe!", Alert.AlertType.ERROR);
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
        FormFactory formFactory = new AddCarFactory();
        Form form = formFactory.createForm();
        form.start();
    }
}
