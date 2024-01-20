package com.projects.taxicorporation.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerBuilderTest {

    @Test
    public void testManagerBuilder() {
        // Arrange
        ManagerBuilder managerBuilder = new ManagerBuilder();

        // Act
        managerBuilder.createNewUser();
        managerBuilder.buildUsername("managerUser");
        managerBuilder.buildPassword("managerPassword");
        managerBuilder.buildRank("Manager");
        managerBuilder.buildEmail("manager@example.com");
        managerBuilder.buildDepartment("Administration");
        managerBuilder.buildCity("CityA");
        managerBuilder.buildStreet("Street123");

        Manager manager = (Manager) managerBuilder.getUser();

        // Assert
        assertEquals("managerUser", manager.getUsername());
        assertEquals("managerPassword", manager.getPassword());
        assertEquals("Manager", manager.getRank());
        assertEquals("manager@example.com", manager.getEmail());
        assertEquals("Administration", manager.getDepartment());
        assertEquals("CityA", manager.getCity());
        assertEquals("Street123", manager.getStreet());
    }
}
