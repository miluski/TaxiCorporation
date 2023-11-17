package com.projects.taxicorporation.client;
public class DriverPanelController {
    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
    }
    public void onAcceptJobButtonClicked() {
    }
}
