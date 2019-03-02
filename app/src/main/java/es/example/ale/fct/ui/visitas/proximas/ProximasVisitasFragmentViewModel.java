package es.example.ale.fct.ui.visitas.proximas;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import es.example.ale.fct.data.Repository;
import es.example.ale.fct.data.model.Alumno;
import es.example.ale.fct.data.model.Visita;

public class ProximasVisitasFragmentViewModel extends ViewModel {
    private final Repository repository;
    private MutableLiveData<List<Visita>> proximasVistas;
    private List<Visita> lstProximasVisitas;
    private LiveData<Visita> ultimaVisita;

    public ProximasVisitasFragmentViewModel(Repository repository) {
        this.repository = repository;
        proximasVistas = new MutableLiveData<>();
        lstProximasVisitas = new ArrayList<>();
    }

    public LiveData<List<Alumno>> getAlumnos(){
        return repository.queryAllAlumnos();
    }

    public LiveData<Visita> getUltimaVisita(String nombreAlumno){
        return repository.queryUltimaVisita(nombreAlumno);
    }

    public void setProximaVisita(Visita visita){
        lstProximasVisitas.add(visita);
        proximasVistas.setValue(lstProximasVisitas);
    }

    public LiveData<List<Visita>> getProximasVisitas(){
        return proximasVistas;
    }
}
