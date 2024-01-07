package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class RenameOrDeleteDepartmentDarkView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("RenameOrDeleteDepartmentDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        RenameOrDeleteDepartmentController renameOrDeleteDepartmentController = fxmlLoader.getController();
        setButtonPrototypesCredentials();
        drawAllButtons(renameOrDeleteDepartmentController);
        renameOrDeleteDepartmentController.fetchDepartmentsData();
        MainStage.getInstance().setTitle("Zmień nazwę lub usuń oddział");
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
    private void drawAllButtons(RenameOrDeleteDepartmentController renameOrDeleteDepartmentController) {
        drawAddManagerButton(renameOrDeleteDepartmentController);
        drawDeleteManagerButton(renameOrDeleteDepartmentController);
        drawManageManagersButton(renameOrDeleteDepartmentController);
        drawAddDepartmentButton(renameOrDeleteDepartmentController);
        drawManageDepartmentsButton(renameOrDeleteDepartmentController);
        drawAddCarButton(renameOrDeleteDepartmentController);
        drawDeleteCarButton(renameOrDeleteDepartmentController);
        drawLogoutButton(renameOrDeleteDepartmentController);
        drawFinallyAcceptChangesButton(renameOrDeleteDepartmentController);
        drawFinallyDeleteDepartmentButton(renameOrDeleteDepartmentController);
    }
    private void drawAddManagerButton(RenameOrDeleteDepartmentController renameOrDeleteDepartmentController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setText("Dodaj menadżera");
        drawButtonDecorator.draw(renameOrDeleteDepartmentController, renameOrDeleteDepartmentController::onAddMenagerButtonClicked);
    }
    private void drawDeleteManagerButton(RenameOrDeleteDepartmentController renameOrDeleteDepartmentController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(119.0d);
        drawButtonDecorator.setText("Usuń menadżera");
        drawButtonDecorator.draw(renameOrDeleteDepartmentController, renameOrDeleteDepartmentController::onDeleteMenagerButtonClicked);
    }
    private void drawManageManagersButton(RenameOrDeleteDepartmentController renameOrDeleteDepartmentController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(162.0d);
        drawButtonDecorator.setText("Zarządzaj menadżerami");
        drawButtonDecorator.draw(renameOrDeleteDepartmentController, renameOrDeleteDepartmentController::onManageMenagersButtonClicked);
    }
    private void drawAddDepartmentButton(RenameOrDeleteDepartmentController renameOrDeleteDepartmentController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(205.0d);
        drawButtonDecorator.setText("Dodaj oddział");
        drawButtonDecorator.draw(renameOrDeleteDepartmentController, renameOrDeleteDepartmentController::onAddDepartmentButtonClicked);
    }
    private void drawManageDepartmentsButton(RenameOrDeleteDepartmentController renameOrDeleteDepartmentController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(250.0d);
        drawButtonDecorator.setText("Zarządzaj oddziałami");
        drawButtonDecorator.draw(renameOrDeleteDepartmentController, null);
    }
    private void drawAddCarButton(RenameOrDeleteDepartmentController renameOrDeleteDepartmentController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(296.0d);
        drawButtonDecorator.setText("Dodaj samochód");
        drawButtonDecorator.draw(renameOrDeleteDepartmentController, renameOrDeleteDepartmentController::onAddCarButtonClicked);
    }
    private void drawDeleteCarButton(RenameOrDeleteDepartmentController renameOrDeleteDepartmentController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(340.0d);
        drawButtonDecorator.setText("Usuń samochód");
        drawButtonDecorator.draw(renameOrDeleteDepartmentController, renameOrDeleteDepartmentController::onDeleteCarButtonClicked);
    }
    private void drawLogoutButton(RenameOrDeleteDepartmentController renameOrDeleteDepartmentController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(renameOrDeleteDepartmentController, renameOrDeleteDepartmentController::onLogoutButtonClicked);
    }
    private void drawFinallyAcceptChangesButton(RenameOrDeleteDepartmentController renameOrDeleteDepartmentController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(354.0d);
        drawButtonDecorator.setText("Zatwierdź edycję");
        drawButtonDecorator.draw(renameOrDeleteDepartmentController, renameOrDeleteDepartmentController::onConfirmDepartmentEditButton);
    }
    private void drawFinallyDeleteDepartmentButton(RenameOrDeleteDepartmentController renameOrDeleteDepartmentController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(402.0d);
        drawButtonDecorator.setText("Usuń");
        drawButtonDecorator.draw(renameOrDeleteDepartmentController, renameOrDeleteDepartmentController::onDeleteChoosedDepartmentButtonClicked);
    }
}
