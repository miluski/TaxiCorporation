package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class DeleteManagerView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("DeleteManager.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        DeleteMenagerController deleteMenagerController = fxmlLoader.getController();
        setButtonPrototypesCredentials();
        drawAllButtons(deleteMenagerController);
        deleteMenagerController.fetchManagersData();
        MainStage.getInstance().setTitle("Usuwanie menadżera");
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
    private void drawAllButtons(DeleteMenagerController deleteMenagerController) {
        drawAddManagerButton(deleteMenagerController);
        drawDeleteManagerButton(deleteMenagerController);
        drawManageManagersButton(deleteMenagerController);
        drawAddDepartmentButton(deleteMenagerController);
        drawManageDepartmentsButton(deleteMenagerController);
        drawAddCarButton(deleteMenagerController);
        drawDeleteCarButton(deleteMenagerController);
        drawLogoutButton(deleteMenagerController);
        drawFinallyDeleteManagerButton(deleteMenagerController);
    }
    private void drawAddManagerButton(DeleteMenagerController deleteMenagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setText("Dodaj menadżera");
        drawButtonDecorator.draw(deleteMenagerController, deleteMenagerController::onAddManagerButtonClicked);
    }
    private void drawDeleteManagerButton(DeleteMenagerController deleteMenagerController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(119.0d);
        drawButtonDecorator.setText("Usuń menadżera");
        drawButtonDecorator.draw(deleteMenagerController, null);
    }
    private void drawManageManagersButton(DeleteMenagerController deleteMenagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(162.0d);
        drawButtonDecorator.setText("Zarządzaj menadżerami");
        drawButtonDecorator.draw(deleteMenagerController, deleteMenagerController::onManageManagersButtonClicked);
    }
    private void drawAddDepartmentButton(DeleteMenagerController deleteMenagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(205.0d);
        drawButtonDecorator.setText("Dodaj oddział");
        drawButtonDecorator.draw(deleteMenagerController, deleteMenagerController::onAddDepartmentButtonClicked);
    }
    private void drawManageDepartmentsButton(DeleteMenagerController deleteMenagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(250.0d);
        drawButtonDecorator.setText("Zarządzaj oddziałami");
        drawButtonDecorator.draw(deleteMenagerController, deleteMenagerController::onMenageDepartmentsButtonClicked);
    }
    private void drawAddCarButton(DeleteMenagerController deleteMenagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(296.0d);
        drawButtonDecorator.setText("Dodaj samochód");
        drawButtonDecorator.draw(deleteMenagerController, deleteMenagerController::onAddCarButtonClicked);
    }
    private void drawDeleteCarButton(DeleteMenagerController deleteMenagerController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(340.0d);
        drawButtonDecorator.setText("Usuń samochód");
        drawButtonDecorator.draw(deleteMenagerController, deleteMenagerController::onDeleteCarButtonClicked);
    }
    private void drawLogoutButton(DeleteMenagerController deleteMenagerController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(deleteMenagerController, deleteMenagerController::onLogoutButtonClicked);
    }
    private void drawFinallyDeleteManagerButton(DeleteMenagerController deleteMenagerController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(374.0d);
        drawButtonDecorator.setText("Usuń");
        drawButtonDecorator.draw(deleteMenagerController, deleteMenagerController::onEndDeleteMenagerButtonClicked);
    }
}
