package es.example.ale.fct.ui.formularioEmpresa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.example.ale.fct.data.Repository;
import es.example.ale.fct.data.model.Empresa;

public class FormEmpresaViewModelFragment extends ViewModel {
    private final Repository repository;

    public FormEmpresaViewModelFragment(Repository repository) {
        this.repository = repository;
    }

    public void insertEmpresa(Empresa empresa){
        repository.insertEmpresa(empresa);
    }

    public LiveData<Empresa> getSelectedEmpresa(long id){
        return repository.queryEmpresa(id);
    }

    public void updateEmpresa(Empresa empresa){
        repository.updateEmpresa(empresa);
    }
}
