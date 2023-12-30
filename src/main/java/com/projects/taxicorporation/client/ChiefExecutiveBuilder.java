package com.projects.taxicorporation.client;

public class ChiefExecutiveBuilder extends UserBuilder {
    @Override
    public void createNewUser() {
        this.user = new ChiefExecutive();
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

    @Override
    public void buildDepartment(String department) {
        this.user.department = department;
    }

    @Override
    public void buildCity(String city) {
        this.user.city = city;
    }

    @Override
    public void buildStreet(String street) {
        this.user.street = street;
    }
}
