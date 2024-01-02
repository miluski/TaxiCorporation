package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.*;
import javafx.scene.layout.AnchorPane;

public class ShowMapController implements Controller {
    public MapView mapView;
    public AnchorPane buttonsAnchorPane;

    public void onFindRouteButtonClicked() throws Exception {
        FormFactory formFactory = new ClientPanelFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
    }

    @Override
    public AnchorPane getButtonsAnchorPane() {
        return null;
    }
}
