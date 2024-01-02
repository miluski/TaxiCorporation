package com.projects.taxicorporation.client;

public class LightTheme extends ThemeStrategy {
    @Override
    public void applyTheme() {
        MainStage.getInstance().setThemeName("Light");
    }
}
