package es.example.ale.fct.ui.formularioAlumno;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import es.example.ale.fct.R;
import es.example.ale.fct.data.RepositoryImpl;
import es.example.ale.fct.data.local.AlumnoDao;
import es.example.ale.fct.data.local.AppDatabase;
import es.example.ale.fct.data.model.Alumno;
import es.example.ale.fct.databinding.FragmentFormAlumnoBinding;
import es.example.ale.fct.utils.ValidationUtils;


public class FormAlumnoFragment extends Fragment {

    private FragmentFormAlumnoBinding formBinding;
    private FormAlumnoViewModelFragment viewModel;
    private NavController navController;
    public FormAlumnoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        formBinding = FragmentFormAlumnoBinding.inflate(inflater,container,false);
        return formBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        AlumnoDao alumnoDao = AppDatabase.getInstance(getContext()).alumnoDao();
        RepositoryImpl repository = new RepositoryImpl(alumnoDao);

        viewModel = ViewModelProviders.of(this,new FormAlumnoViewModelFactoryFragment(repository)).get(FormAlumnoViewModelFragment.class);
        initViews();
    }

    private void initViews() {
        formBinding.fab.setOnClickListener(v -> validar());
    }

    private void validar() {
        boolean validarNombre = request(formBinding.txtName);
        boolean validarTelefono = request(formBinding.txtTelefono);
        boolean validarEmail = request(formBinding.txtMail);
        boolean validarCurso = request(formBinding.txtCurso);

        if(validarNombre && validarTelefono && validarEmail && validarCurso){
            insertAlumno();
        }
    }

    private void insertAlumno() {
        String telefono = formBinding.txtTelefono.getText().toString();
        int numTelf = Integer.parseInt(telefono);
        viewModel.newAlumno(new Alumno(formBinding.txtName.getText().toString(),numTelf,formBinding.txtMail.getText().toString(),formBinding.txtCurso.getText().toString()));
        navController.popBackStack();
    }

    private boolean request(TextInputEditText text){
        if(text.getId() == formBinding.txtMail.getId()){
            if(!ValidationUtils.isValidEmail(text.getText().toString())){
                text.setError(getString(R.string.invalidEmail));
                return false;
            }
        }
        else if(text.getId() != formBinding.txtTelefono.getId() || text.getId() != formBinding.txtTelfTutor.getId()){
            if(TextUtils.isEmpty(text.getText().toString())){
                text.setError(getString(R.string.requiredCampo));
                return false;
            }
        }
        else{
            if(!ValidationUtils.isValidPhone(text.getText().toString())){
                text.setError(getString(R.string.invalidNumber));
                return false;
            }
        }
        return true;
    }
}
