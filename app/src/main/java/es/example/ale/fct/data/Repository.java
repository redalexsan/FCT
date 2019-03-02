package es.example.ale.fct.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import es.example.ale.fct.data.model.Alumno;
import es.example.ale.fct.data.model.Empresa;
import es.example.ale.fct.data.model.Visita;

public interface Repository {

    //Alumnos
    LiveData<List<Alumno>> queryAllAlumnos();
    LiveData<Alumno> queryAlumno(long alumnoID);
    void insertAlumno(Alumno alumno);
    void deleteAlumno(Alumno alumno);
    void updateAlumno(Alumno alumno);

    //Empresas
    LiveData<List<Empresa>> queryAllEmpresas();
    LiveData<Empresa> queryEmpresa(long empresaID);
    void insertEmpresa(Empresa empresa);
    void deleteEmpresa(Empresa empresa);
    void updateEmpresa(Empresa empresa);

    //Visitas
    LiveData<List<Visita>> queryAllVisitas();
    LiveData<Visita> queryVisita(int visitaID);
    void insertVisita(Visita visita);
    void deleteVisita(Visita visita);
    void updateVisita(Visita visita);
    LiveData<Visita> queryUltimaVisita(String nombre);
}
