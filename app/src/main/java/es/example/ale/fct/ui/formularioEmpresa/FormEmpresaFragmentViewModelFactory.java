package es.example.ale.fct.ui.formularioEmpresa;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.example.ale.fct.data.Repository;
import es.example.ale.fct.data.RepositoryImpl;

class FormEmpresaFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public FormEmpresaFragmentViewModelFactory(RepositoryImpl repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FormEmpresaViewModelFragment(repository);
    }
}
