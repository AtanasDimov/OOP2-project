module com.example.librarysoftware {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.sql;


    opens com.example.librarysoftware to javafx.fxml;
    exports com.example.librarysoftware;
}