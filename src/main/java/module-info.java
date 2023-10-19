module com.projects.korporacjataxi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires com.sothawo.mapjfx;
    requires org.slf4j;

    opens com.projects.taxicorporation;
    exports com.projects.taxicorporation;
}