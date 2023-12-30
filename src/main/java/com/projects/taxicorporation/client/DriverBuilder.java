package com.projects.taxicorporation.client;

public class DriverBuilder extends UserBuilder {
    @Override
    public void createNewUser() {
        this.user = new Driver();
    }

    @Override
    public void buildUsername(String userName) {
        this.user.userName = userName;
    }

    @Override
    public void buildPassword(String password) {
        this.user.password = password;
    }

    @Override
    public void buildRank(String rank) {
        this.user.rank = rank;
    }

    @Override
    public void buildEmail(String email) {
        this.user.email = email;
    }

}
