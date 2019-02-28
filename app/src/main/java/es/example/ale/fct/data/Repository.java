package es.example.ale.fct.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import es.example.ale.fct.data.model.Alumno;

public interface Repository {

    LiveData<List<Alumno>> queryAllAlumnos();
    LiveData<Alumno> queryAlumno(long alumnoID);
    void insertAlumno(Alumno alumno);
    void deleteAlumno(Alumno alumno);
    void updateAlumno(Alumno alumno);
}
