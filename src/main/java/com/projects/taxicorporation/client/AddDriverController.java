package com.projects.taxicorporation.client;

public class AddDriverController {
    public void onAddDriverButtonClicked() throws Exception {
        FormFactory formFactory = new AddDriverFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onHireDriverButtonClicked() throws Exception {
        FormFactory formFactory = new DeleteDriverFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onManageDriversButtonClicked() throws Exception {
        FormFactory formFactory = new DriverPanelFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
    }
    public void onEndDriverAddButton() {
    }
}
