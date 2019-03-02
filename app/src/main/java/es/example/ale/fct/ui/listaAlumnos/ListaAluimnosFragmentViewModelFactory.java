package es.example.ale.fct.ui.listaAlumnos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.example.ale.fct.data.Repository;

public class ListaAluimnosFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public ListaAluimnosFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListAlumnosFragmentViewModel(repository);
    }
}
