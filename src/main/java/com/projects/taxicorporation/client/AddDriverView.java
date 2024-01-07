package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class AddDriverView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("AddDriver.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddDriverController addDriverController = fxmlLoader.getController();
        setButtonPrototypesCredentials();
        drawAllButtons(addDriverController);
        addDriverController.fetchDepartmentsData();
        MainStage.getInstance().setTitle("Dodawanie kierowcy");
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
    private void drawAllButtons(AddDriverController addDriverController) {
        drawAddDriverButton(addDriverController);
        drawHireDriverButton(addDriverController);
        drawManageDriversButton(addDriverController);
        drawMapButton(addDriverController);
        drawLogoutButton(addDriverController);
        drawFinallyAddDriverButton(addDriverController);
    }
    private void drawAddDriverButton(AddDriverController addDriverController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(119.0d);
        drawButtonDecorator.setText("Dodaj kierowcę");
        drawButtonDecorator.draw(addDriverController, null);
    }
    private void drawHireDriverButton(AddDriverController addDriverController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(162.0d);
        drawButtonDecorator.setText("Zwolnij kierowcę");
        drawButtonDecorator.draw(addDriverController, addDriverController::onHireDriverButtonClicked);
    }
    private void drawManageDriversButton(AddDriverController addDriverController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(205.0d);
        drawButtonDecorator.setText("Zarządzaj kierowcami");
        drawButtonDecorator.draw(addDriverController, addDriverController::onManageDriversButtonClicked);
    }
    private void drawMapButton(AddDriverController addDriverController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(250.0d);
        drawButtonDecorator.setText("Mapa");
        drawButtonDecorator.draw(addDriverController, addDriverController::onMapButtonClicked);
    }
    private void drawLogoutButton(AddDriverController addDriverController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(addDriverController, addDriverController::onLogoutButtonClicked);
    }
    private void drawFinallyAddDriverButton(AddDriverController addDriverController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(344.0d);
        drawButtonDecorator.setText("Dodaj");
        drawButtonDecorator.draw(addDriverController, addDriverController::onEndDriverAddButton);
    }
}
