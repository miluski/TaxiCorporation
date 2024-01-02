package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class ManageManagerView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("ManageManager.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setButtonPrototypesCredentials();
        drawAllButtons(fxmlLoader.getController());
        MainStage.getInstance().setTitle("Zarządzanie menadżerami");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
    private void setButtonPrototypesCredentials() throws CloneNotSupportedException {
        lightRedButtonPrototype.setHeight(35.0d);
        lightRedButtonPrototype.setWidth(215.0d);
        lightRedButtonPrototype.setX(13.0d);
        lightRedButtonPrototype.setY(74.0d);
        redButtonPrototype = lightRedButtonPrototype.clone();
        grayButtonPrototype = lightRedButtonPrototype.clone();
    }
    private void drawAllButtons(ManageManagerController manageManagerController) {
        drawAddManagerButton(manageManagerController);
        drawDeleteManagerButton(manageManagerController);
        drawManageManagersButton(manageManagerController);
        drawAddDepartmentButton(manageManagerController);
        drawManageDepartmentsButton(manageManagerController);
        drawAddCarButton(manageManagerController);
        drawDeleteCarButton(manageManagerController);
        drawLogoutButton(manageManagerController);
        drawFinallyAssignManagerButton(manageManagerController);
    }
    private void drawAddManagerButton(ManageManagerController manageManagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setText("Dodaj menadżera");
        drawButtonDecorator.draw(manageManagerController, manageManagerController::onAddMenagerButtonClicked);
    }
    private void drawDeleteManagerButton(ManageManagerController manageManagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(119.0d);
        drawButtonDecorator.setText("Usuń menadżera");
        drawButtonDecorator.draw(manageManagerController, manageManagerController::onDeleteMenagerButtonClicked);
    }
    private void drawManageManagersButton(ManageManagerController manageManagerController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(162.0d);
        drawButtonDecorator.setText("Zarządzaj menadżerami");
        drawButtonDecorator.draw(manageManagerController, null);
    }
    private void drawAddDepartmentButton(ManageManagerController manageManagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(205.0d);
        drawButtonDecorator.setText("Dodaj oddział");
        drawButtonDecorator.draw(manageManagerController, manageManagerController::onAddDepartmentButtonClicked);
    }
    private void drawManageDepartmentsButton(ManageManagerController manageManagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(250.0d);
        drawButtonDecorator.setText("Zarządzaj oddziałami");
        drawButtonDecorator.draw(manageManagerController, manageManagerController::onDeleteDepartmentButtonClicked);
    }
    private void drawAddCarButton(ManageManagerController manageManagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(296.0d);
        drawButtonDecorator.setText("Dodaj samochód");
        drawButtonDecorator.draw(manageManagerController, manageManagerController::onAddCarButtonClicked);
    }
    private void drawDeleteCarButton(ManageManagerController manageManagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(340.0d);
        drawButtonDecorator.setText("Usuń samochód");
        drawButtonDecorator.draw(manageManagerController, manageManagerController::onDeleteCarButtonClicked);
    }
    private void drawLogoutButton(ManageManagerController manageManagerController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(manageManagerController, manageManagerController::onLogoutButtonClicked);
    }
    private void drawFinallyAssignManagerButton(ManageManagerController manageManagerController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(374.0d);
        drawButtonDecorator.setText("Przydziel");
        drawButtonDecorator.draw(manageManagerController, manageManagerController::onEndAssignMenagerButtonClicked);
    }
}
