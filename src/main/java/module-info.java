module com.example.gestordetareas {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.gestordetareas to javafx.fxml;
    opens models to javafx.base;
    exports com.example.gestordetareas;
}