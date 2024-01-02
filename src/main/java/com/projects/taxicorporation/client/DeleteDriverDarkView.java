package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class DeleteDriverDarkView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("DeleteDriverDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setButtonPrototypesCredentials();
        drawAllButtons(fxmlLoader.getController());
        MainStage.getInstance().setTitle("Usuwanie kierowcy");
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
    private void drawAllButtons(DeleteDriverController deleteDriverController) {
        drawAddDriverButton(deleteDriverController);
        drawHireDriverButton(deleteDriverController);
        drawManageDriversButton(deleteDriverController);
        drawMapButton(deleteDriverController);
        drawLogoutButton(deleteDriverController);
        drawFinallyHireDriverButton(deleteDriverController);
    }
    private void drawAddDriverButton(DeleteDriverController deleteDriverController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(119.0d);
        drawButtonDecorator.setText("Dodaj kierowcę");
        drawButtonDecorator.draw(deleteDriverController, deleteDriverController::onAddDriverButtonClicked);
    }
    private void drawHireDriverButton(DeleteDriverController deleteDriverController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(162.0d);
        drawButtonDecorator.setText("Zwolnij kierowcę");
        drawButtonDecorator.draw(deleteDriverController, null);
    }
    private void drawManageDriversButton(DeleteDriverController deleteDriverController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(205.0d);
        drawButtonDecorator.setText("Zarządzaj kierowcami");
        drawButtonDecorator.draw(deleteDriverController, deleteDriverController::onMenageDriversButtonClicked);
    }
    private void drawMapButton(DeleteDriverController deleteDriverController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(250.0d);
        drawButtonDecorator.setText("Mapa");
        drawButtonDecorator.draw(deleteDriverController, deleteDriverController::onMapButtonClicked);
    }
    private void drawLogoutButton(DeleteDriverController deleteDriverController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(deleteDriverController, deleteDriverController::onLogoutButtonClicked);
    }
    private void drawFinallyHireDriverButton(DeleteDriverController deleteDriverController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(344.0d);
        drawButtonDecorator.setText("Zwolnij");
        drawButtonDecorator.draw(deleteDriverController, deleteDriverController::onEndHireDriverButtonClicked);
    }
}
