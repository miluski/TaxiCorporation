package com.projects.taxicorporation.client;

public class ClientPanelController {
    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
    }
    public void onEndSearchButtonClicked() {
    }
}
