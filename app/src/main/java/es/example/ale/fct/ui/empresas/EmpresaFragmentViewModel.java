package es.example.ale.fct.ui.empresas;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.example.ale.fct.data.Repository;
import es.example.ale.fct.data.model.Empresa;

public class EmpresaFragmentViewModel extends ViewModel {

    private Repository repository;
    private final LiveData<List<Empresa>> empresas;

    public EmpresaFragmentViewModel(Repository repository) {
        this.repository = repository;
        empresas = repository.queryAllEmpresas();
    }

    public LiveData<List<Empresa>> getEmpresas() {
        return empresas;
    }

    public void deleteEmpresa(Empresa empresa){ repository.deleteEmpresa(empresa);}
}
