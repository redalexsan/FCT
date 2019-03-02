package es.example.ale.fct.ui.listaAlumnos;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.example.ale.fct.data.Repository;
import es.example.ale.fct.data.model.Alumno;

public class ListAlumnosFragmentViewModel extends ViewModel {

    private final Repository repository;

    public ListAlumnosFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Alumno>> getAlumnos(){
        return repository.queryAllAlumnos();
    }
}
