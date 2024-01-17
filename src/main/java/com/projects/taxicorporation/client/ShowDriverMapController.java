package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.MapView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShowDriverMapController {
    public MapView mapView;
    public boolean isRouteStarted;
    public void onReservationsButtonClicked() throws Exception {
        if(!isRouteStarted) {
            FormFactory formFactory = new ChooseRoadFactory();
            Form form = formFactory.createForm();
            form.start();
        }
        else
            AlertDialog.getInstance().setParametersAndShow("Posiadasz rozpoczętą podróż!", Alert.AlertType.WARNING);
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
    public void onLogoutButtonClicked() {
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }

}
