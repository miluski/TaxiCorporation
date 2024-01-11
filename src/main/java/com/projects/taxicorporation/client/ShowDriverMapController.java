package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.MapView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class ShowDriverMapController {
    public MapView mapView;

    public void onReservationsButtonClicked() throws Exception {
        FormFactory formFactory = new DriverPanelFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new ShowDriverMapFactory("", "");
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }


}
