package es.example.ale.fct.ui.alumnos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.example.ale.fct.data.Repository;
import es.example.ale.fct.data.model.Alumno;

public class AlumnosFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public AlumnosFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AlumnosFragmentViewModel(repository);
    }
}
