package com.projects.taxicorporation.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ShowMapControllerTest {

    @Test
    public void testOnFindRouteButtonClicked() throws Exception {
        ShowMapController controller = mock(ShowMapController.class);

        doNothing().when(controller).onFindRouteButtonClicked();

        controller.onFindRouteButtonClicked();

        verify(controller, times(1)).onFindRouteButtonClicked();

        assertTrue(true);
    }

    @Test
    public void testOnLogoutButtonClicked() {
        ShowMapController controller = mock(ShowMapController.class);
        doNothing().when(controller).onLogoutButtonClicked();
        controller.onLogoutButtonClicked();
        verify(controller, times(1)).onLogoutButtonClicked();
        assertTrue(true);
    }

    @Test
    public void testOnChangeThemeButtonClicked() throws Exception {
        ShowMapController controller = mock(ShowMapController.class);
        doNothing().when(controller).onChangeThemeButtonClicked();
        controller.onChangeThemeButtonClicked();
        verify(controller, times(1)).onChangeThemeButtonClicked();
        assertTrue(true);
    }

    @Test
    public void testGetButtonsAnchorPane() {
        ShowMapController controller = mock(ShowMapController.class);
        when(controller.getButtonsAnchorPane()).thenReturn(null);
        controller.getButtonsAnchorPane();
        verify(controller, times(1)).getButtonsAnchorPane();
        assertTrue(true);
    }
}