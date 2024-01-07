package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class AddManagerDarkView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("AddManagerDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddManagerController addManagerController = fxmlLoader.getController();
        setButtonPrototypesCredentials();
        drawAllButtons(addManagerController);
        addManagerController.fetchDepartmentsData();
        MainStage.getInstance().setTitle("Dodawanie menadżera");
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
    private void drawAllButtons(AddManagerController addManagerController) {
        drawAddManagerButton(addManagerController);
        drawDeleteManagerButton(addManagerController);
        drawManageManagersButton(addManagerController);
        drawAddDepartmentButton(addManagerController);
        drawManageDepartmentsButton(addManagerController);
        drawAddCarButton(addManagerController);
        drawDeleteCarButton(addManagerController);
        drawLogoutButton(addManagerController);
        drawFinallyAddManagerButton(addManagerController);
    }
    private void drawAddManagerButton(AddManagerController addManagerController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setText("Dodaj menadżera");
        drawButtonDecorator.draw(addManagerController, null);
    }
    private void drawDeleteManagerButton(AddManagerController addManagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(119.0d);
        drawButtonDecorator.setText("Usuń menadżera");
        drawButtonDecorator.draw(addManagerController, addManagerController::onDeleteMenagerButtonClicked);
    }
    private void drawManageManagersButton(AddManagerController addManagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(162.0d);
        drawButtonDecorator.setText("Zarządzaj menadżerami");
        drawButtonDecorator.draw(addManagerController, addManagerController::onMenageMenagersButtonClicked);
    }
    private void drawAddDepartmentButton(AddManagerController addManagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(205.0d);
        drawButtonDecorator.setText("Dodaj oddział");
        drawButtonDecorator.draw(addManagerController, addManagerController::onAddDepartmentButtonClicked);
    }
    private void drawManageDepartmentsButton(AddManagerController addManagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(250.0d);
        drawButtonDecorator.setText("Zarządzaj oddziałami");
        drawButtonDecorator.draw(addManagerController, addManagerController::onDeleteDepartmentButtonClicked);
    }
    private void drawAddCarButton(AddManagerController addManagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(296.0d);
        drawButtonDecorator.setText("Dodaj samochód");
        drawButtonDecorator.draw(addManagerController, addManagerController::onAddCarButtonClicked);
    }
    private void drawDeleteCarButton(AddManagerController addManagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(340.0d);
        drawButtonDecorator.setText("Usuń samochód");
        drawButtonDecorator.draw(addManagerController, addManagerController::onDeleteCarButtonClicked);
    }
    private void drawLogoutButton(AddManagerController addManagerController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(addManagerController, addManagerController::onLogoutButtonClicked);
    }
    private void drawFinallyAddManagerButton(AddManagerController addManagerController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(374.0d);
        drawButtonDecorator.setText("Dodaj");
        drawButtonDecorator.draw(addManagerController, addManagerController::onEndAddMenagerButtonClicked);
    }
}
