package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.MapView;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
    Wzorzec projektowy singleton
 */
public class MainStage {
    private static final MainStage instance = new MainStage();
    private static final Stage mainStage = StartForm.getMainStage();
    private static MapView proxyMapView;
    private static String themeName = "Light";
    private MainStage() {
        ProxyMap proxyMap = new ProxyMap();
        proxyMap.displayMap();
        proxyMapView = ProxyMap.mapView;
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
    protected void setThemeName(String themeName) {
        MainStage.themeName = themeName;
    }
    protected String getThemeName() {
        return themeName;
    }
    protected MapView getProxyMapView() {
        return proxyMapView;
    }
    protected void show() {
        mainStage.show();
    }
}
