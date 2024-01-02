package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class DriverPanelDarkView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("DriverPanelDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setButtonPrototypesCredentials();
        drawAllButtons(fxmlLoader.getController());
        MainStage.getInstance().setTitle("Panel kierowcy");
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
    private void drawAllButtons(DriverPanelController driverPanelController) {
        drawMyOrdersButton(driverPanelController);
        drawMapButton(driverPanelController);
        drawLogoutButton(driverPanelController);
        drawAcceptOrderFinalButton(driverPanelController);
    }
    private void drawMyOrdersButton(DriverPanelController driverPanelController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(119.0d);
        drawButtonDecorator.setText("Wyszukaj trasę");
        drawButtonDecorator.draw(driverPanelController, null);
    }
    private void drawMapButton(DriverPanelController driverPanelController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(156.0d);
        drawButtonDecorator.setText("Mapa");
        drawButtonDecorator.draw(driverPanelController, driverPanelController::onMapButtonClicked);
    }
    private void drawLogoutButton(DriverPanelController driverPanelController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(driverPanelController, driverPanelController::onLogoutButtonClicked);
    }
    private void drawAcceptOrderFinalButton(DriverPanelController driverPanelController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setY(374.0d);
        drawButtonDecorator.setText("Przyjmij");
        drawButtonDecorator.draw(driverPanelController, driverPanelController::onAcceptJobButtonClicked);
    }
}
