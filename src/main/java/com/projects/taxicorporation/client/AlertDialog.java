package com.projects.taxicorporation.client;

import javafx.scene.control.Alert;

/**
 * Wzorzec projektowy singleton
 */
public class AlertDialog {
    private static final AlertDialog alertDialog = new AlertDialog();
    private final Alert alert;
    private AlertDialog() {
        this.alert = new Alert(Alert.AlertType.NONE);
    }
    protected static AlertDialog getInstance() {
        return alertDialog;
    }
    protected void setParametersAndShow(String message, Alert.AlertType alertType) {
        alert.setAlertType(alertType);
        alert.setTitle("Informacja");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
