module com.example.librarysoftware {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.persistence;
    requires org.hibernate.orm.core;

    opens com.example.librarysoftware to javafx.fxml;
    exports com.example.librarysoftware;
}