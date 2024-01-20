package com.projects.taxicorporation.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MechanicBuilderTest {

    @Test
    public void testMechanicBuilder() {
        // Arrange
        MechanicBuilder mechanicBuilder = new MechanicBuilder();

        // Act
        mechanicBuilder.createNewUser();
        mechanicBuilder.buildUsername("mechanicUser");
        mechanicBuilder.buildPassword("mechanicPassword");
        mechanicBuilder.buildRank("Mechanic");
        mechanicBuilder.buildEmail("mechanic@example.com");
        mechanicBuilder.buildDepartment("Engineering");

        Mechanic mechanic = (Mechanic) mechanicBuilder.getUser();

        // Assert
        assertEquals("mechanicUser", mechanic.getUsername());
        assertEquals("mechanicPassword", mechanic.getPassword());
        assertEquals("Mechanic", mechanic.getRank());
        assertEquals("mechanic@example.com", mechanic.getEmail());
        assertEquals("Engineering", mechanic.getDepartment());
    }
}
