package es.example.ale.fct.ui.listaEmpresas;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.example.ale.fct.data.Repository;

public class ListaEmpresasFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public ListaEmpresasFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListaEmpresasFragmentViewModel(repository);
    }
}
