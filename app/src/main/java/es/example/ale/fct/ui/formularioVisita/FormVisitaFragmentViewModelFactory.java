package es.example.ale.fct.ui.formularioVisita;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.example.ale.fct.data.Repository;

public class FormVisitaFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public FormVisitaFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FormVisitaFragmentViewModel(repository);
    }
}
