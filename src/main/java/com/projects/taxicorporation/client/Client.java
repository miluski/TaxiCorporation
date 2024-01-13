package com.projects.taxicorporation.client;

public class Client extends User {
    public String getUsername() {
        return this.userName;
    }
    public String getUserId() { return this.userId; }
    public String getPassword() {
        return this.password;
    }
    public String getRank() {
        return this.rank;
    }
    public String getEmail() {
        return this.email;
    }
}
