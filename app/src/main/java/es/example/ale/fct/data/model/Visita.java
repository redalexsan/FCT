package es.example.ale.fct.data.model;

import androidx.room.*;

//, foreignKeys = {@ForeignKey(entity = Alumno.class,parentColumns = "id",childColumns = "codAlumno")}
@Entity(tableName = "visita")

public class Visita {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nombreAlumno")
    private String nombreAlumno;
    @ColumnInfo(name = "observaciones")
    private String observaciones;
    @ColumnInfo(name = "horaInicio")
    private String horaInicio;
    @ColumnInfo(name = "horaFin")
    private String horaFin;
    @ColumnInfo(name = "dia")
    private String dia;

    public Visita(String nombreAlumno, String observaciones, String horaInicio, String horaFin, String dia) {
        this.nombreAlumno = nombreAlumno;
        this.observaciones = observaciones;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dia = dia;
    }

    @Ignore
    public Visita(String nombreAlumno,String dia){
        this.nombreAlumno = nombreAlumno;
        this.dia = dia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
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

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
