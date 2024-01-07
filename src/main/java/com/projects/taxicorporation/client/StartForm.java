package com.projects.taxicorporation.client;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Objects;

public class StartForm extends Application {
    private static Stage mainStage;

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        mainStage.setResizable(false);
        mainStage.setOnCloseRequest(windowEvent -> handleCloseWindowEvent());
        FormFactory formFactory = new LoginFormFactory();
        Form form = formFactory.createForm();
        MainStage.getInstance().setThemeName("Light");
        form.start();
    }

    private void handleCloseWindowEvent() {
        if (MainStage.getInstance().getUser().getUsername() != null &&
                !Objects.equals(MainStage.getInstance().getUser().getUsername(), ""))
        {
            UserOperations userOperations = new UserOperations();
            UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
            userFacade.logOutUser();
        }
    }
}