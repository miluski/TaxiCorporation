package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class DeleteCarDarkView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("DeleteCarDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setButtonPrototypesCredentials();
        drawAllButtons(fxmlLoader.getController());
        MainStage.getInstance().setTitle("Usuwanie samochodu");
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
    private void drawAllButtons(DeleteCarController deleteCarController) {
        drawAddManagerButton(deleteCarController);
        drawDeleteManagerButton(deleteCarController);
        drawManageManagersButton(deleteCarController);
        drawAddDepartmentButton(deleteCarController);
        drawManageDepartmentsButton(deleteCarController);
        drawAddCarButton(deleteCarController);
        drawDeleteCarButton(deleteCarController);
        drawLogoutButton(deleteCarController);
        drawFinallyAddCarButton(deleteCarController);
    }
    private void drawAddManagerButton(DeleteCarController deleteCarController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setText("Dodaj menadżera");
        drawButtonDecorator.draw(deleteCarController, deleteCarController::onAddMenagerButtonClicked);
    }
    private void drawDeleteManagerButton(DeleteCarController deleteCarController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(119.0d);
        drawButtonDecorator.setText("Usuń menadżera");
        drawButtonDecorator.draw(deleteCarController, deleteCarController::onDeleteMenagerButtonClicked);
    }
    private void drawManageManagersButton(DeleteCarController deleteCarController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(162.0d);
        drawButtonDecorator.setText("Zarządzaj menadżerami");
        drawButtonDecorator.draw(deleteCarController, deleteCarController::onMenageMenagersButtonClicked);
    }
    private void drawAddDepartmentButton(DeleteCarController deleteCarController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(205.0d);
        drawButtonDecorator.setText("Dodaj oddział");
        drawButtonDecorator.draw(deleteCarController, deleteCarController::onAddDepartmentButtonClicked);
    }
    private void drawManageDepartmentsButton(DeleteCarController deleteCarController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(250.0d);
        drawButtonDecorator.setText("Zarządzaj oddziałami");
        drawButtonDecorator.draw(deleteCarController, deleteCarController::onMenageDepartmentsButtonClicked);
    }
    private void drawAddCarButton(DeleteCarController deleteCarController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(296.0d);
        drawButtonDecorator.setText("Dodaj samochód");
        drawButtonDecorator.draw(deleteCarController, deleteCarController::onAddCarButtonClicked);
    }
    private void drawDeleteCarButton(DeleteCarController deleteCarController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(340.0d);
        drawButtonDecorator.setText("Usuń samochód");
        drawButtonDecorator.draw(deleteCarController, null);
    }
    private void drawLogoutButton(DeleteCarController deleteCarController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(deleteCarController, deleteCarController::onLogoutButtonClicked);
    }
    private void drawFinallyAddCarButton(DeleteCarController deleteCarController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(374.0d);
        drawButtonDecorator.setText("Usuń");
        drawButtonDecorator.draw(deleteCarController, deleteCarController::onEndDeleteCarButtonClicked);
    }
}
