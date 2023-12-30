package com.projects.taxicorporation.client;

public abstract class UserBuilder {
    User user;
    public User getUser() {
        return this.user;
    }
    public abstract void createNewUser();
    public abstract void buildUsername(String userName);
    public abstract void buildPassword(String password);
    public abstract void buildRank(String rank);
    public abstract void buildEmail(String email);
    public void buildDepartment(String department) {};
    public void buildCity(String city) {};
    public  void buildStreet(String street) {};
}
