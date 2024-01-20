package com.projects.taxicorporation.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ThemeStrategyTest {

    private DarkTheme darkTheme;
    private LightTheme lightTheme;
    private MainStage mainStage;

    @BeforeEach
    void setUp() {
        darkTheme = new DarkTheme();
        lightTheme = new LightTheme();
        mainStage = MainStage.getInstance(); // Używamy singletona MainStage
    }

    @Test
    void testApplyDarkTheme() {
        darkTheme.applyTheme();
        assertEquals("Dark", mainStage.getThemeName(), "Theme should be Dark after applying DarkTheme");
    }

    @Test
    void testApplyLightTheme() {
        lightTheme.applyTheme();
        assertEquals("Light", mainStage.getThemeName(), "Theme should be Light after applying LightTheme");
    }
}
