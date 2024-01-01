package com.projects.taxicorporation.client;

public class ThemeContext {
    private ThemeStrategy themeStrategy;

    public void setThemeStrategy(ThemeStrategy themeStrategy) {
        this.themeStrategy = themeStrategy;
    }

    public void applyTheme() {
        themeStrategy.applyTheme();
    }
}
