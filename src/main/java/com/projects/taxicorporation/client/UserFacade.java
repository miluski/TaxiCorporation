package com.projects.taxicorporation.client;

public class UserFacade {
    private final User user;
    private final UserOperations userOperations;
    public UserFacade(User user, UserOperations userOperations) {
        this.user = user;
        this.userOperations = userOperations;
    }
    public String logOutUser() {
        userOperations.logOut(getUserData());
        return "User " + user.getUsername() + " logged out.";
    }
    private String getUserData() {
        return user.getUsername();
    }
}
