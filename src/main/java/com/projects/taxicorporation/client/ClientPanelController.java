package com.projects.taxicorporation.client;

import javafx.scene.input.MouseEvent;

public class ClientPanelController {
    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onFindRouteButtonClicked(MouseEvent mouseEvent) throws Exception {
        /*
        FormFactory chooseRoadFactory = new ChooseRoadFactory();
        Form form = chooseRoadFactory.createForm();
        form.start();
        */
    }
    public void onLogoutButtonClicked() {
    }
    public void onEndSearchButtonClicked() {
    }
}
