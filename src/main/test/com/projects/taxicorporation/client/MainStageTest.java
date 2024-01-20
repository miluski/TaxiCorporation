package com.projects.taxicorporation.client;

import com.dlsc.formsfx.model.structure.Section;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
class MainStageTest {

    @Test
    void getInstance_SingletonBehavior_ReturnsSameInstance() {
        MainStage firstInstance = MainStage.getInstance();
        MainStage secondInstance = MainStage.getInstance();
        assertSame(firstInstance, secondInstance, "MainStage should return the same instance");
    }

    @Test
    void setAndGetThemeName_ChangesThemeName_ThemeNameIsUpdated() {
        MainStage mainStage = MainStage.getInstance();
        mainStage.setThemeName("Dark");
        assertEquals("Dark", mainStage.getThemeName(), "Theme name should be updated to 'Dark'");
    }

    @Test
    public void testSingletonInstance() {
        MainStage instance1 = MainStage.getInstance();
        MainStage instance2 = MainStage.getInstance();
        assertSame(instance1, instance2, "MainStage should only have one instance.");
    }

    @Test
    public void testThemeName() {
        String testTheme = "Dark";
        MainStage.getInstance().setThemeName(testTheme);
        assertEquals(testTheme, MainStage.getInstance().getThemeName(), "Theme name should be set and retrieved correctly.");
    }

    @Test
    public void testGetProxyMapView() {
        assertNotNull(MainStage.getInstance().getProxyMapView(), "ProxyMapView should be initialized and not null.");
    }

    @Test
    public void testGetUser() {
        User user = MainStage.getInstance().getUser();
        assertNotNull(user, "User should not be null.");
        assertTrue(user instanceof Client, "User should be an instance of Client.");
    }
    @Test
    public void testShow() {
        // This test should check if mainStage is displayed when show() is called
        // Due to the nature of GUI, this might be more suitable for an integration test
    }

}
