module com.projects.taxicorporation {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.testfx;
    requires org.testfx.junit5;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.sothawo.mapjfx;
    requires org.slf4j;
    requires java.sql;
    requires com.oracle.database.jdbc;

    opens com.projects.taxicorporation.client to javafx.fxml, javafx.graphics, org.testfx.junit5;
    exports com.projects.taxicorporation.client;
}