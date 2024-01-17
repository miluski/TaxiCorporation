package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class ClientPanelDarkView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("ClientPanelDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setButtonPrototypesCredentials();
        drawAllButtons(fxmlLoader.getController());
        MainStage.getInstance().setTitle("Panel klienta");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
    private void setButtonPrototypesCredentials() throws CloneNotSupportedException {
        lightRedButtonPrototype.setHeight(35.0d);
        lightRedButtonPrototype.setWidth(180.0d);
        lightRedButtonPrototype.setX(13.0d);
        lightRedButtonPrototype.setY(189.0d);
        redButtonPrototype = lightRedButtonPrototype.clone();
        grayButtonPrototype = lightRedButtonPrototype.clone();
    }
    private void drawAllButtons(ClientPanelController clientPanelController) {
        drawFindRouteButton(clientPanelController);
        drawMapButton(clientPanelController);
        drawLogoutButton(clientPanelController);
        drawFindRouteFinalButton(clientPanelController);
    }
    private void drawFindRouteButton(ClientPanelController clientPanelController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setText("Zarezerwuj przejazd");
        drawButtonDecorator.draw(clientPanelController, null);
    }
    private void drawMapButton(ClientPanelController clientPanelController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(234.0d);
        drawButtonDecorator.setText("Mapa");
        drawButtonDecorator.draw(clientPanelController, clientPanelController::onMapButtonClicked);
    }
    private void drawLogoutButton(ClientPanelController clientPanelController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(clientPanelController, clientPanelController::onLogoutButtonClicked);
    }
    private void drawFindRouteFinalButton(ClientPanelController clientPanelController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setX(450.0d);
        drawButtonDecorator.setY(374.0d);
        drawButtonDecorator.setText("Zarezerwuj przejazd");
        drawButtonDecorator.draw(clientPanelController, clientPanelController::onEndReservateButtonClicked);
    }
}