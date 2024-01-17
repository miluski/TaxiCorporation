package com.projects.taxicorporation.client;

import java.io.Serializable;

public abstract class User implements Serializable {
    protected String userId = "";
    protected String userName = "";
    protected String password = "";
    protected String rank = "";
    protected String email = "";
    protected String department = "";
    protected String city = "";
    protected String street = "";
    public abstract String getUsername();
    public abstract String getEmail();
}
