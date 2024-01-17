package com.projects.taxicorporation.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ShowManagerMapControllerTest {

    @Test
    public void testOnFindRouteButtonClicked() {
        ShowManagerMapController controller = mock(ShowManagerMapController.class);
        doNothing().when(controller).onFindRouteButtonClicked();
        controller.onFindRouteButtonClicked();
        verify(controller, times(1)).onFindRouteButtonClicked();
        assertTrue(true);
    }

    @Test
    public void testOnLogoutButtonClicked() {
        ShowManagerMapController controller = mock(ShowManagerMapController.class);
        doNothing().when(controller).onLogoutButtonClicked();
        controller.onLogoutButtonClicked();
        verify(controller, times(1)).onLogoutButtonClicked();
        assertTrue(true);
    }

    @Test
    public void testOnChangeThemeButtonClicked() throws Exception {
        ShowManagerMapController controller = mock(ShowManagerMapController.class);
        doNothing().when(controller).onChangeThemeButtonClicked();
        controller.onChangeThemeButtonClicked();
        verify(controller, times(1)).onChangeThemeButtonClicked();
        assertTrue(true);
    }

    @Test
    public void testOnAddDriverButtonClicked() throws Exception {
        ShowManagerMapController controller = mock(ShowManagerMapController.class);
        doNothing().when(controller).onAddDriverButtonClicked();
        controller.onAddDriverButtonClicked();
        verify(controller, times(1)).onAddDriverButtonClicked();
        assertTrue(true);
    }
}