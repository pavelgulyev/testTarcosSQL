module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    opens com.example.demo1.Model to javafx.fxml;
    exports com.example.demo1.Model;
    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.Controller;
    opens com.example.demo1.Controller to javafx.fxml;
    exports com.example.demo1.DAO;
    opens com.example.demo1.DAO to javafx.fxml;
//    exports com.example.program.Repository;
//    opens com.example.program.Repository to javafx.fxml;
}