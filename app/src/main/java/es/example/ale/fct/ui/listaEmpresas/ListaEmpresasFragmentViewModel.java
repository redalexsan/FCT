package es.example.ale.fct.ui.listaEmpresas;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.example.ale.fct.data.Repository;
import es.example.ale.fct.data.model.Empresa;

public class ListaEmpresasFragmentViewModel extends ViewModel {
    private final Repository repository;

    public ListaEmpresasFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Empresa>> getEmpresas(){
        return repository.queryAllEmpresas();
    }
}
