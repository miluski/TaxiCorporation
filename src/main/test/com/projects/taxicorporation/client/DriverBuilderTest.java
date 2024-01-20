package com.projects.taxicorporation.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DriverBuilderTest {

    @Test
    public void testDriverBuilder() {
        // Arrange
        DriverBuilder driverBuilder = new DriverBuilder();

        // Act
        driverBuilder.createNewUser();
        driverBuilder.buildUsername("driverUser");
        driverBuilder.buildPassword("driverPassword");
        driverBuilder.buildRank("Driver");
        driverBuilder.buildEmail("driver@example.com");

        Driver driver = (Driver) driverBuilder.getUser();

        // Assert
        assertEquals("driverUser", driver.getUsername());
        assertEquals("driverPassword", driver.getPassword());
        assertEquals("Driver", driver.getRank());
        assertEquals("driver@example.com", driver.getEmail());
    }
}
