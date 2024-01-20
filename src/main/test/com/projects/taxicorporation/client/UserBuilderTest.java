package com.projects.taxicorporation.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserBuilderTest {

    @Test
    public void testUserBuilder() {
        UserBuilderMock userBuilderMock = new UserBuilderMock();

        userBuilderMock.createNewUser();
        userBuilderMock.buildUsername("testUser");
        userBuilderMock.buildPassword("testPassword");
        userBuilderMock.buildRank("testRank");
        userBuilderMock.buildEmail("test@example.com");

        User user = userBuilderMock.getUser();

        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
    }

}

class UserBuilderMock extends UserBuilder {
    @Override
    public void createNewUser() {
        user = new UserMock();
    }

    @Override
    public void buildUsername(String userName) {
        user.userName = userName;
    }

    @Override
    public void buildPassword(String password) {
        user.password = password;
    }

    @Override
    public void buildRank(String rank) {
        user.rank = rank;
    }

    @Override
    public void buildEmail(String email) {
        user.email = email;
    }
}

class UserMock extends User {
    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
