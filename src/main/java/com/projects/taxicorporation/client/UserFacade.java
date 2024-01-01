package com.projects.taxicorporation.client;

public class UserFacade {
    private final User user;
    private final UserOperations userOperations;

    public UserFacade(User user, UserOperations userOperations) {
        this.user = user;
        this.userOperations = userOperations;
    }

    public String getUserData() {
        return "Login: " + user.getUsername() + "\n" +
                "Email: " + user.getEmail() + "\n";
    }

    public String logOutUser() {
        userOperations.logOut();
        return "User " + user.getUsername() + " logged out.";
    }
}
