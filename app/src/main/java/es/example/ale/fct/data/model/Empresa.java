package es.example.ale.fct.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "empresa",
        indices = {@Index(value = {"nombre"},unique=true)})
public class Empresa {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "cif")
    private String cif;
    @ColumnInfo(name = "direccion")
    private String direccion;
    @ColumnInfo(name = "telf")
    private int telf;
    @ColumnInfo(name = "logo")
    private String logoURL;
    @ColumnInfo(name = "nombre_contacto")
    private String nombreContacto;

    public Empresa(String nombre, String cif, String direccion, int telf, String logoURL, String nombreContacto) {
        this.nombre = nombre;
        this.cif = cif;
        this.direccion = direccion;
        this.telf = telf;
        this.logoURL = logoURL;
        this.nombreContacto = nombreContacto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelf() {
        return telf;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }
}
