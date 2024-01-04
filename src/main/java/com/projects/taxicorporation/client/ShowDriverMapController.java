package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.MapView;

import java.util.Objects;

public class ShowDriverMapController {
    public MapView mapView;

    public void onFindRouteButtonClicked() {
    }

    public void onLogoutButtonClicked() {
    }

    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new ShowDriverMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }
}
