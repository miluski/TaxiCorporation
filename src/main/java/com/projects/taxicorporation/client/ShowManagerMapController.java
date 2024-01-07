package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.MapView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class ShowManagerMapController {
    public MapView mapView;

    public void onFindRouteButtonClicked() {
    }

    public void onLogoutButtonClicked() {
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }

    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new ShowManagerMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onAddDriverButtonClicked() throws Exception {
        FormFactory formFactory = new AddDriverFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onMenageDriversButtonClicked() throws Exception {
        FormFactory formFactory = new ManageDriversFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowManagerMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }
}
