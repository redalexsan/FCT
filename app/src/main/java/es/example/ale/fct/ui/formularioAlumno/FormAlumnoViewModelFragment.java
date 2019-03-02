package es.example.ale.fct.ui.formularioAlumno;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.example.ale.fct.data.Repository;
import es.example.ale.fct.data.model.Alumno;

public class FormAlumnoViewModelFragment extends ViewModel {

    private final Repository repository;

    public FormAlumnoViewModelFragment(Repository repository) {
        this.repository = repository;
    }

    public void newAlumno(Alumno alumno){
        repository.insertAlumno(alumno);
    }

    public LiveData<Alumno> getSelectedAlumno(String id){
        return repository.queryAlumno(Long.parseLong(id));
    }

    public void updateAlumno(Alumno alumno){
        repository.updateAlumno(alumno);
    }
}
