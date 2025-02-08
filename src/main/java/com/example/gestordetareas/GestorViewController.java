package com.example.gestordetareas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import models.Tarea;



public class GestorViewController {

    @FXML
    private Button agregarBtn;

    @FXML
    private TextField descripcionField;

    @FXML
    private TableColumn<Tarea, String> eliminarColumn;

    @FXML
    private TableColumn<Tarea, Boolean> marcarColumn;

    @FXML
    private TableColumn<Tarea, String> tareasColumn;

    @FXML
    private TableView<Tarea> tareasTable;
    private ObservableList<Tarea> tareas;
    private Button eliminarBtn;

    public void initialize() {
        // Inicializar la lista observable de tareas
        tareas = FXCollections.observableArrayList();


        // Configurar la columna "tareasColumn" para mostrar la descripción de la tarea
        tareasColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tareasColumn.setCellFactory(column -> {
            return new TableCell<Tarea, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle(""); // Limpiar estilos si no hay contenido
                    } else {
                        setText(item); // Mostrar la descripción de la tarea

                        // Obtener la tarea correspondiente a esta fila
                        Tarea tarea = getTableView().getItems().get(getIndex());

                        // Aplicar estilo si la tarea está completada
                        if (tarea.isCompletada()) {
                            setStyle("-fx-text-fill: gray; -fx-strikethrough: true; -fx-background-color: #f0f0f0;"); // Texto gris y tachado
                        } else {
                            setStyle(""); // Limpiar estilos si no está completada
                        }
                    }
                }
            };
        });


        // Configurar la columna "marcarColumn" para mostrar un CheckBox
        marcarColumn.setCellValueFactory(cellData -> cellData.getValue().completadaProperty());
        marcarColumn.setStyle("-fx-alignment: CENTER");
        marcarColumn.setCellFactory(column -> {
            return new TableCell<Tarea, Boolean>() {
                private final CheckBox checkBox = new CheckBox();

                @Override
                protected void updateItem(Boolean isCompleted, boolean empty) {
                    super.updateItem(isCompleted, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        // Vincular el estado del CheckBox al atributo booleano de la Tarea
                        checkBox.setSelected(isCompleted);
                        setGraphic(checkBox);

                        // Escuchar cambios en el CheckBox para actualizar el estado de la Tarea
                        checkBox.setOnAction(event -> {
                            Tarea tarea = getTableView().getItems().get(getIndex());
                            tarea.setCompletada(checkBox.isSelected());

                            // Refrescar la tabla para actualizar la interfaz
                            tareasTable.refresh();
                        });
                    }
                }
            };
        });

        // Configurar la columna "eliminarColumn" para mostrar un botón de eliminación
        eliminarColumn.setStyle("-fx-alignment: CENTER");
        eliminarColumn.setCellFactory(column -> {
            return new TableCell<Tarea, String>() {
                private final Button deleteButton = new Button("X");


                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                        deleteButton.setOnAction(event -> {
                            Tarea tarea = getTableView().getItems().get(getIndex());
                            tareas.remove(tarea);
                        });
                    }
                }
            };
        });

        // Asignar la lista observable a la TableView
        tareasTable.setItems(tareas);
        tareasTable.setSelectionModel(null);

    }

    public void agregarTarea() {
        // Obtener la descripción del TextField

        String descripcion = descripcionField.getText().trim();

        if (!descripcion.isEmpty()) {
            // Crear una nueva tarea y agregarla a la lista
            Tarea nuevaTarea = new Tarea(descripcion);
            tareas.add(nuevaTarea);

            // Limpiar el TextField después de agregar la tarea
            descripcionField.clear();
        } else {
            // Mostrar un mensaje de error si el campo está vacío
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo vacío");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, ingresa una descripción para la tarea.");
            alert.showAndWait();
        }
    }

}