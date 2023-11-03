package com.projects.taxicorporation.client;

import javafx.application.Application;
import javafx.stage.Stage;

public class StartForm extends Application {
    private static Stage mainStage;
    public static Stage getMainStage() {
        return mainStage;
    }
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        FormFactory formFactory = new LoginFormFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public static void main(String[] args) {
        launch();
    }
}