package es.example.ale.fct.data.local;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import es.example.ale.fct.data.model.Alumno;

@Dao
public interface AlumnoDao {

    @Query("SELECT * FROM alumno")
    LiveData<List<Alumno>> queryAllAlumnos();

    @Query("SELECT * FROM alumno WHERE id = :alumnoID")
    LiveData<Alumno> queryAlumno(long alumnoID);

    @Insert
    void insertAlumno(Alumno alumno);

    @Delete
    void deleteAlumno(Alumno alumno);

    @Update
    void updateAlumno(Alumno alumno);
}
