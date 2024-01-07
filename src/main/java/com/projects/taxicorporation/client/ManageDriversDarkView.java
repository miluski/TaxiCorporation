package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class ManageDriversDarkView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("ManageDriversDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setButtonPrototypesCredentials();
        drawAllButtons(fxmlLoader.getController());
        MainStage.getInstance().setTitle("Zarządzanie kierowcami");
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
    private void drawAllButtons(ManageDriversController manageDriversController) {
        drawAddDriverButton(manageDriversController);
        drawHireDriverButton(manageDriversController);
        drawManageDriversButton(manageDriversController);
        drawMapButton(manageDriversController);
        drawLogoutButton(manageDriversController);
        drawFinallyAssignButton(manageDriversController);
    }
    private void drawAddDriverButton(ManageDriversController manageDriversController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(119.0d);
        drawButtonDecorator.setText("Dodaj kierowcę");
        drawButtonDecorator.draw(manageDriversController, manageDriversController::onAddDriverButtonClicked);
    }
    private void drawHireDriverButton(ManageDriversController manageDriversController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(162.0d);
        drawButtonDecorator.setText("Zwolnij kierowcę");
        drawButtonDecorator.draw(manageDriversController, manageDriversController::onHireDriverButtonClicked);
    }
    private void drawManageDriversButton(ManageDriversController manageDriversController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(205.0d);
        drawButtonDecorator.setText("Zarządzaj kierowcami");
        drawButtonDecorator.draw(manageDriversController, null);
    }
    private void drawMapButton(ManageDriversController manageDriversController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(250.0d);
        drawButtonDecorator.setText("Mapa");
        drawButtonDecorator.draw(manageDriversController, manageDriversController::onMapButtonClicked);
    }
    private void drawLogoutButton(ManageDriversController manageDriversController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(manageDriversController, manageDriversController::onLogoutButtonClicked);
    }
    private void drawFinallyAssignButton(ManageDriversController manageDriversController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(344.0d);
        drawButtonDecorator.setText("Przydziel");
        drawButtonDecorator.draw(manageDriversController, manageDriversController::onFinallyAssignButtonClicked);
    }
}
