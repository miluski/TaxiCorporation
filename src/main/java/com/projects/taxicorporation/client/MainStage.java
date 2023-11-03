package com.projects.taxicorporation.client;

import javafx.scene.Scene;
import javafx.stage.Stage;
/*
    Wzorzec projektowy singleton
 */
public class MainStage {
    private static final MainStage instance = new MainStage();
    private static final Stage mainStage = StartForm.getMainStage();
    private MainStage() {
        if(instance!=null)
            throw new RuntimeException("Not allowed operation. Use getInstance() instead.");
    }
    public static MainStage getInstance() {
        return instance;
    }
    protected void setTitle(String title) {
        mainStage.setTitle(title);
    }
    protected void setScene(Scene scene) {
        mainStage.setScene(scene);
    }
    protected void show() {
        mainStage.show();
    }
}
