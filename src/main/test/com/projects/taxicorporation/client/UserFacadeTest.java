package com.projects.taxicorporation.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserFacadeTest {

    @Test
    public void testLogOutUser() {
        // Arrange
        User user = mock(User.class);
        UserOperations userOperations = mock(UserOperations.class);
        UserFacade userFacade = new UserFacade(user, userOperations);
        when(user.getUsername()).thenReturn("testUser");

        // Act
        String result = userFacade.logOutUser();

        // Assert
        verify(userOperations).logOut("testUser");
        assertEquals("User testUser logged out.", result);
    }
}
