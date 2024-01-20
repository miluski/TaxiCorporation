package com.projects.taxicorporation.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientBuilderTest {

    @Test
    public void testClientBuilder() {
        // Arrange
        ClientBuilder clientBuilder = new ClientBuilder();

        // Act
        clientBuilder.createNewUser();
        clientBuilder.buildUsername("clientUser");
        clientBuilder.buildPassword("clientPassword");
        clientBuilder.buildRank("Client");
        clientBuilder.buildEmail("client@example.com");

        Client client = (Client) clientBuilder.getUser();

        // Assert
        assertEquals("clientUser", client.getUsername());
        assertEquals("", client.getUserId()); // Default value from User class
        assertEquals("clientPassword", client.getPassword());
        assertEquals("Client", client.getRank());
        assertEquals("client@example.com", client.getEmail());
    }
}
