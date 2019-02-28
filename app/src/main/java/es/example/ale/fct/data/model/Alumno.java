package es.example.ale.fct.data.model;

import androidx.room.*;

@Entity(tableName = "alumno",indices = {@Index(value = {"nombre"},unique=true)})
public class Alumno {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "telf")
    private int telf;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "curso")
    private String curso;

    private String empresa;

    @ColumnInfo(name = "nombre_tutor")
    private String nombreTutor;
    @ColumnInfo(name = "telf_tutor")
    private int telfTutor;
    @ColumnInfo(name = "horario")
    private String horario;

    public Alumno(String nombre, int telf, String email, String curso) {
        this.nombre = nombre;
        this.telf = telf;
        this.email = email;
        this.curso = curso;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTelf() {
        return telf;
    }

    public String getEmail() {
        return email;
    }

    public String getCurso() {
        return curso;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public int getTelfTutor() {
        return telfTutor;
    }

    public String getHorario() {
        return horario;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public void setTelfTutor(int telfTutor) {
        this.telfTutor = telfTutor;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
