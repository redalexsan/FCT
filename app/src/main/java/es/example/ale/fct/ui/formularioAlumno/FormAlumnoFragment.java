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
    private String idAlumnoArg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            FormAlumnoFragmentArgs args = FormAlumnoFragmentArgs.fromBundle(getArguments());
            idAlumnoArg = args.getIdAlumno();
        }
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
        if(!TextUtils.equals(idAlumnoArg,null)){
            fillDatas();
        }
        formBinding.fab.setOnClickListener(v -> validar());
    }

    private void fillDatas() {
        viewModel.getSelectedAlumno(idAlumnoArg).observe(this, alumno -> {
            formBinding.txtName.setText(alumno.getNombre());
            formBinding.txtTelefono.setText(String.valueOf(alumno.getTelf()));
            formBinding.txtMail.setText(alumno.getEmail());
            formBinding.txtCurso.setText(alumno.getCurso());
            if(!TextUtils.equals(alumno.getEmpresa(),null))
                formBinding.txtEmpresa.setText(alumno.getEmpresa());
            if(!TextUtils.equals(alumno.getNombreTutor(),null))
                formBinding.txtNombreTutor.setText(alumno.getNombreTutor());
            if(alumno.getTelfTutor() != 0)
                formBinding.txtTelfTutor.setText(String.valueOf(alumno.getTelfTutor()));
            if(!TextUtils.equals(alumno.getHorario(),null))
                formBinding.txtHorario.setText(alumno.getHorario());
        });
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
        Alumno alumno = new Alumno(formBinding.txtName.getText().toString(),Integer.parseInt(formBinding.txtTelefono.getText().toString()),formBinding.txtMail.getText().toString(),formBinding.txtCurso.getText().toString());

        if(!TextUtils.isEmpty(formBinding.txtEmpresa.getText().toString()))
            alumno.setEmpresa(formBinding.txtEmpresa.getText().toString());
        if(!TextUtils.isEmpty(formBinding.txtNombreTutor.getText().toString()))
            alumno.setNombreTutor(formBinding.txtNombreTutor.getText().toString());
        if(!TextUtils.isEmpty(formBinding.txtTelfTutor.getText().toString()))
            alumno.setTelfTutor(Integer.parseInt(formBinding.txtTelfTutor.getText().toString()));
        if(!TextUtils.isEmpty(formBinding.txtHorario.getText().toString()))
            alumno.setHorario(formBinding.txtHorario.getText().toString());

        if(!TextUtils.equals(idAlumnoArg,null)){
            alumno.setId(Long.parseLong(idAlumnoArg));
            viewModel.updateAlumno(alumno);
        }
        else
            viewModel.newAlumno(alumno);
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
