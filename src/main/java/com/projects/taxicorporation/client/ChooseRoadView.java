package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class ChooseRoadView extends Form {
    private final ButtonPrototype lightRedButtonPrototype = new LightRedButtonPrototype();
    private ButtonPrototype redButtonPrototype;
    private ButtonPrototype grayButtonPrototype;
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("ChooseRoad.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setButtonPrototypesCredentials();
        drawAllButtons(fxmlLoader.getController());
        MainStage.getInstance().setTitle("Wybieranie trasy");
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
    private void drawAllButtons(ChooseRoadController chooseRoadController) {
        drawFindRouteButton(chooseRoadController);
        drawMapButton(chooseRoadController);
        drawLogoutButton(chooseRoadController);
    }
    private void drawFindRouteButton(ChooseRoadController chooseRoadController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(119.0d);
        drawButtonDecorator.setText("Rezerwacje");
        drawButtonDecorator.draw(chooseRoadController, null);
    }
    private void drawMapButton(ChooseRoadController chooseRoadController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(156.0d);
        drawButtonDecorator.setText("Mapa");
        drawButtonDecorator.draw(chooseRoadController, chooseRoadController::onMapButtonClicked);
    }
    private void drawLogoutButton(ChooseRoadController chooseRoadController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(chooseRoadController, chooseRoadController::onLogoutButtonClicked);
    }
}
