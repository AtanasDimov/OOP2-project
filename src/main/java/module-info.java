module com.example.librarysoftware {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens com.example.librarysoftware to javafx.fxml;
    exports com.example.librarysoftware;
}