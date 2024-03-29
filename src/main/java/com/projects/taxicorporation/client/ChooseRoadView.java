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
        ChooseRoadController chooseRoadController = fxmlLoader.getController();
        chooseRoadController.communicateWithServer("GetAvailableReservations");
        setButtonPrototypesCredentials();
        drawAllButtons(chooseRoadController);
        MainStage.getInstance().setTitle("Wybieranie trasy");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }

    private void setButtonPrototypesCredentials() throws CloneNotSupportedException {
        lightRedButtonPrototype.setHeight(35.0d);
        lightRedButtonPrototype.setWidth(180.0d);
        lightRedButtonPrototype.setX(13.0d);
        lightRedButtonPrototype.setY(74.0d);
        redButtonPrototype = lightRedButtonPrototype.clone();
        grayButtonPrototype = lightRedButtonPrototype.clone();
    }
    private void drawAllButtons(ChooseRoadController chooseRoadController) {
        drawFindRouteButton(chooseRoadController);
        drawMapButton(chooseRoadController);
        drawLogoutButton(chooseRoadController);
        drawChooseCourseButton(chooseRoadController);
    }
    private void drawFindRouteButton(ChooseRoadController chooseRoadController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(189.0d);
        drawButtonDecorator.setText("Rezerwacje");
        drawButtonDecorator.draw(chooseRoadController, null);
    }
    private void drawMapButton(ChooseRoadController chooseRoadController) {
        ButtonDecorator drawButtonDecorator = new LightRedButtonDecorator(lightRedButtonPrototype);
        drawButtonDecorator.setY(234.0d);
        drawButtonDecorator.setText("Mapa");
        drawButtonDecorator.draw(chooseRoadController, chooseRoadController::onMapButtonClicked);
    }
    private void drawLogoutButton(ChooseRoadController chooseRoadController) {
        ButtonDecorator drawButtonDecorator = new GrayButtonDecorator(grayButtonPrototype);
        drawButtonDecorator.setY(419.0d);
        drawButtonDecorator.setText("Wyloguj");
        drawButtonDecorator.draw(chooseRoadController, chooseRoadController::onLogoutButtonClicked);
    }

    private void drawChooseCourseButton(ChooseRoadController chooseRoadController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setY(400.0d);
        drawButtonDecorator.setX(400.0d);
        drawButtonDecorator.setText("Wybierz");
        drawButtonDecorator.draw(chooseRoadController, chooseRoadController::onChooseCourseClicked);
    }
}
