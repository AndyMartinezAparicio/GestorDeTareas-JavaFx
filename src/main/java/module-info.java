module com.example.gestordetareas {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.gestordetareas to javafx.fxml;
    exports com.example.gestordetareas;
}