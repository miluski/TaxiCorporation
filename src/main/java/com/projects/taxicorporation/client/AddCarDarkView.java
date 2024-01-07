package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class AddCarDarkView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("AddCarDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddCarController addCarController = fxmlLoader.getController();
        setButtonPrototypesCredentials();
        drawAllButtons(addCarController);
        addCarController.fetchDepartmentsData();
        MainStage.getInstance().setTitle("Dodawanie samochodu");
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
    private void drawAllButtons(AddCarController addCarController) {
        drawAddManagerButton(addCarController);
        drawDeleteManagerButton(addCarController);
        drawManageManagersButton(addCarController);
        drawAddDepartmentButton(addCarController);
        drawManageDepartmentsButton(addCarController);
        drawAddCarButton(addCarController);
        drawDeleteCarButton(addCarController);
        drawLogoutButton(addCarController);
        drawFinallyAddCarButton(addCarController);
    }
    private void drawAddManagerButton(AddCarController addCarController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setText("Dodaj menadżera");
        drawButtonDecorator.draw(addCarController, addCarController::onAddMenagerButtonClicked);
    }
    private void drawDeleteManagerButton(AddCarController addCarController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(119.0d);
        drawButtonDecorator.setText("Usuń menadżera");
        drawButtonDecorator.draw(addCarController, addCarController::onDeleteMenagerButtonClicked);
    }
    private void drawManageManagersButton(AddCarController addCarController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(162.0d);
        drawButtonDecorator.setText("Zarządzaj menadżerami");
        drawButtonDecorator.draw(addCarController, addCarController::onMenageMenagersButtonClicked);
    }
    private void drawAddDepartmentButton(AddCarController addCarController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(205.0d);
        drawButtonDecorator.setText("Dodaj oddział");
        drawButtonDecorator.draw(addCarController, addCarController::onAddDepartmentButtonClicked);
    }
    private void drawManageDepartmentsButton(AddCarController addCarController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(250.0d);
        drawButtonDecorator.setText("Zarządzaj oddziałami");
        drawButtonDecorator.draw(addCarController, addCarController::onMenageDepartmentsButtonClicked);
    }
    private void drawAddCarButton(AddCarController addCarController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(296.0d);
        drawButtonDecorator.setText("Dodaj samochód");
        drawButtonDecorator.draw(addCarController, null);
    }
    private void drawDeleteCarButton(AddCarController addCarController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(340.0d);
        drawButtonDecorator.setText("Usuń samochód");
        drawButtonDecorator.draw(addCarController, addCarController::onDeleteCarButtonClicked);
    }
    private void drawLogoutButton(AddCarController addCarController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(addCarController, addCarController::onLogoutButtonClicked);
    }
    private void drawFinallyAddCarButton(AddCarController addCarController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(374.0d);
        drawButtonDecorator.setText("Dodaj");
        drawButtonDecorator.draw(addCarController, addCarController::onEndAddCarButtonClicked);
    }
}
