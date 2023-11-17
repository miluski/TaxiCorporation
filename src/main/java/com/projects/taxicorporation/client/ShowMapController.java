package com.projects.taxicorporation.client;
public class ShowMapController {
    public void onFindRouteButtonClicked() throws Exception {
        FormFactory formFactory = new ClientPanelFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
    }
}
