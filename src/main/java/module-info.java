module Project3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires javafx.graphics;


    exports project.model;
    opens project.model to javafx.fxml;
    exports project.controller;
    opens project.controller to javafx.fxml;
    exports project.Manager;
    opens project.Manager to javafx.fxml;

}