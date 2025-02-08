package models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Tarea {
    private String descripcion;
    private BooleanProperty completada;

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = new SimpleBooleanProperty(false); // Por defecto, la tarea no está completada
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada.get();
    }

    public void setCompletada(boolean completada) {
        this.completada.set(completada);
    }

    public BooleanProperty completadaProperty() {
        return completada;
    }
}