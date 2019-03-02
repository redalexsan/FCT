package es.example.ale.fct.ui.alumnos;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.example.ale.fct.data.Repository;
import es.example.ale.fct.data.model.Alumno;

public class AlumnosFragmentViewModel extends ViewModel {

    private final Repository repository;
    private final LiveData<List<Alumno>> alumnos;

    public AlumnosFragmentViewModel(Repository repository) {
        this.repository = repository;
        this.alumnos = repository.queryAllAlumnos();
    }

    LiveData<List<Alumno>> getAlumnos() { return  alumnos; }

    void deleteAlumno(Alumno alumno){ repository.deleteAlumno(alumno);}
}
