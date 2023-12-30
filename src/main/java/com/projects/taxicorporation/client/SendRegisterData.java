package com.projects.taxicorporation.client;

import java.util.ArrayList;
import java.util.List;

public class SendRegisterData {
    private final UserBuilder userBuilder;
    private final List<String> userData = new ArrayList<>();

    SendRegisterData(UserBuilder userBuilder, List<String> userData) {
        this.userBuilder = userBuilder;
        this.userBuilder.createNewUser();
        this.userData.addAll(userData);
    }

    public User build() {
        if(this.userBuilder instanceof ChiefExecutiveBuilder)
            return buildChiefExecutive();
        else if(this.userBuilder instanceof ManagerBuilder)
            return buildManager();
        else if(this.userBuilder instanceof TechnicalWorkerBuilder)
            return buildTechnicalWorker();
        else if(this.userBuilder instanceof DriverBuilder)
            return buildDriver();
        else if(this.userBuilder instanceof ClientBuilder)
            return buildClient();
        else if(this.userBuilder instanceof MechanicBuilder)
            return buildMechanic();
        return null;
    }

    private User buildChiefExecutive() {
        this.userBuilder.buildUsername(userData.get(0));
        this.userBuilder.buildPassword(userData.get(1));
        this.userBuilder.buildEmail(userData.get(2));
        this.userBuilder.buildRank(userData.get(3));
        this.userBuilder.buildDepartment(userData.get(4));
        this.userBuilder.buildCity(userData.get(5));
        this.userBuilder.buildStreet(userData.get(6));
        return this.userBuilder.getUser();
    }

    private User buildManager() {
        this.userBuilder.buildUsername(userData.get(0));
        this.userBuilder.buildPassword(userData.get(1));
        this.userBuilder.buildEmail(userData.get(2));
        this.userBuilder.buildRank(userData.get(3));
        this.userBuilder.buildDepartment(userData.get(4));
        this.userBuilder.buildCity(userData.get(5));
        this.userBuilder.buildStreet(userData.get(6));
        return this.userBuilder.getUser();
    }

    private User buildTechnicalWorker() {
        this.userBuilder.buildUsername(userData.get(0));
        this.userBuilder.buildPassword(userData.get(1));
        this.userBuilder.buildEmail(userData.get(2));
        this.userBuilder.buildRank(userData.get(3));
        this.userBuilder.buildDepartment(userData.get(4));
        return this.userBuilder.getUser();
    }

    private User buildDriver() {
        this.userBuilder.buildUsername(userData.get(0));
        this.userBuilder.buildPassword(userData.get(1));
        this.userBuilder.buildEmail(userData.get(2));
        this.userBuilder.buildRank(userData.get(3));
        return this.userBuilder.getUser();
    }

    private User buildClient() {
        this.userBuilder.buildUsername(userData.get(0));
        this.userBuilder.buildPassword(userData.get(1));
        this.userBuilder.buildEmail(userData.get(2));
        this.userBuilder.buildRank(userData.get(3));
        return this.userBuilder.getUser();
    }

    private User buildMechanic() {
        this.userBuilder.buildUsername(userData.get(0));
        this.userBuilder.buildPassword(userData.get(1));
        this.userBuilder.buildEmail(userData.get(2));
        this.userBuilder.buildRank(userData.get(3));
        this.userBuilder.buildDepartment(userData.get(4));
        return this.userBuilder.getUser();
    }
}
