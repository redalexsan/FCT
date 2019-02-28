package es.example.ale.fct.data.model;

import androidx.room.*;

//, foreignKeys = {@ForeignKey(entity = Alumno.class,parentColumns = "id",childColumns = "codAlumno")}
@Entity(tableName = "visita")

public class Visita {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "observaciones")
    private String observaciones;

    public Visita(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
