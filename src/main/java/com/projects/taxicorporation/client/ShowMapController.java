package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

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

    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new ShowMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }
}
