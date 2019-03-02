package es.example.ale.fct.ui.empresas;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.example.ale.fct.data.Repository;

public class EmpresaFragmentViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    public EmpresaFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EmpresaFragmentViewModel(repository);
    }
}
