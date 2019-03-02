package es.example.ale.fct;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import es.example.ale.fct.data.model.Empresa;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<String> nombreEmpresaSeleccionada;
    private MutableLiveData<String> nombreAlumnoSeleccionado;

    public MainActivityViewModel() {
        nombreEmpresaSeleccionada = new MutableLiveData<>();
        nombreAlumnoSeleccionado = new MutableLiveData<>();
    }

    public LiveData<String> getNombreEmpresaSeleccionada() {
        return nombreEmpresaSeleccionada;
    }

    public void setNombreEmpresaSeleccionada(String nombreEmpresaSeleccionada) {
        this.nombreEmpresaSeleccionada.setValue(nombreEmpresaSeleccionada);
    }

    public void setNombreAlumnoSeleccionado(String nombre) {
        nombreAlumnoSeleccionado.setValue(nombre);
    }

    public LiveData<String> getNombreAlumnoSeleccionado() {
        return nombreAlumnoSeleccionado;
    }
}
