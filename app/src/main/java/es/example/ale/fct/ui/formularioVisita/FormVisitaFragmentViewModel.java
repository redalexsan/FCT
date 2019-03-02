package es.example.ale.fct.ui.formularioVisita;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.example.ale.fct.data.Repository;
import es.example.ale.fct.data.model.Visita;

public class FormVisitaFragmentViewModel extends ViewModel {

    private final Repository repository;

    public FormVisitaFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public void insertVisita(Visita visita){
        repository.insertVisita(visita);
    }

    public LiveData<Visita> getSelectedVisita(int id){
        return repository.queryVisita(id);
    }

    public void updateVisita(Visita visita){
        repository.updateVisita(visita);
    }
}
