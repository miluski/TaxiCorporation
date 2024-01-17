package com.projects.taxicorporation.client;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowDriverMapControllerTest extends ApplicationTest {

    private ShowDriverMapController showDriverMapController;

    @Override
    public void start(final Stage stage) throws Exception {
        showDriverMapController = new ShowDriverMapController();
    }

    @Test
    void testReservationsButtonClicked() {
        assertThrows(Exception.class, () -> showDriverMapController.onReservationsButtonClicked());
    }

    @Test
    void testChangeThemeButtonClicked() {
        assertThrows(Exception.class, () -> showDriverMapController.onChangeThemeButtonClicked());
    }
}
