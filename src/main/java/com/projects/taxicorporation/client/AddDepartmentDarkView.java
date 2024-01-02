package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class AddDepartmentDarkView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("AddDepartmentDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setButtonPrototypesCredentials();
        drawAllButtons(fxmlLoader.getController());
        MainStage.getInstance().setTitle("Dodawanie oddziału");
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
    private void drawAllButtons(AddDepartmentController addDepartmentController) {
        drawAddManagerButton(addDepartmentController);
        drawDeleteManagerButton(addDepartmentController);
        drawManageManagersButton(addDepartmentController);
        drawAddDepartmentButton(addDepartmentController);
        drawManageDepartmentsButton(addDepartmentController);
        drawAddCarButton(addDepartmentController);
        drawDeleteCarButton(addDepartmentController);
        drawLogoutButton(addDepartmentController);
        drawFinallyAddDepartmentButton(addDepartmentController);
        drawEditOrDeleteDepartmentButton(addDepartmentController);
    }
    private void drawAddManagerButton(AddDepartmentController addDepartmentController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setText("Dodaj menadżera");
        drawButtonDecorator.draw(addDepartmentController, addDepartmentController::onAddMenagerButtonClicked);
    }
    private void drawDeleteManagerButton(AddDepartmentController addDepartmentController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(119.0d);
        drawButtonDecorator.setText("Usuń menadżera");
        drawButtonDecorator.draw(addDepartmentController, addDepartmentController::onDeleteMenagerButtonClicked);
    }
    private void drawManageManagersButton(AddDepartmentController addDepartmentController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(162.0d);
        drawButtonDecorator.setText("Zarządzaj menadżerami");
        drawButtonDecorator.draw(addDepartmentController, addDepartmentController::onMenageMenagersButtonClicked);
    }
    private void drawAddDepartmentButton(AddDepartmentController addDepartmentController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(205.0d);
        drawButtonDecorator.setText("Dodaj oddział");
        drawButtonDecorator.draw(addDepartmentController, null);
    }
    private void drawManageDepartmentsButton(AddDepartmentController addDepartmentController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(250.0d);
        drawButtonDecorator.setText("Zarządzaj oddziałami");
        drawButtonDecorator.draw(addDepartmentController, addDepartmentController::onMenageDepartmentsButtonClicked);
    }
    private void drawAddCarButton(AddDepartmentController addDepartmentController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(296.0d);
        drawButtonDecorator.setText("Dodaj samochód");
        drawButtonDecorator.draw(addDepartmentController, addDepartmentController::onAddCarButtonClicked);
    }
    private void drawDeleteCarButton(AddDepartmentController addDepartmentController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(340.0d);
        drawButtonDecorator.setText("Usuń samochód");
        drawButtonDecorator.draw(addDepartmentController, addDepartmentController::onDeleteCarButtonClicked);
    }
    private void drawLogoutButton(AddDepartmentController addDepartmentController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(addDepartmentController, addDepartmentController::onLogoutButtonClicked);
    }
    private void drawFinallyAddDepartmentButton(AddDepartmentController addDepartmentController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(344.0d);
        drawButtonDecorator.setText("Dodaj");
        drawButtonDecorator.draw(addDepartmentController, addDepartmentController::onEndAddDepartmentButtonClicked);
    }
    private void drawEditOrDeleteDepartmentButton(AddDepartmentController addDepartmentController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(402.0d);
        drawButtonDecorator.setText("Edytuj lub usuń oddział");
        drawButtonDecorator.draw(addDepartmentController, addDepartmentController::onEditOrDeleteDepartmentButtonClicked);
    }
}
