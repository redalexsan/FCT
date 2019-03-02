package es.example.ale.fct.ui.visitas.realizadas;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.example.ale.fct.data.Repository;
import es.example.ale.fct.data.model.Visita;

public class VisitasRealizadasFragmentViewModel extends ViewModel {

    private final Repository repository;
    private final LiveData<List<Visita>> visitas;

    public VisitasRealizadasFragmentViewModel(Repository repository) {
        this.repository = repository;
        visitas = repository.queryAllVisitas();
    }


    public LiveData<List<Visita>> getVisitas() {
        return visitas;
    }

    public void deleteVisita(Visita visita){ repository.deleteVisita(visita);}
}
