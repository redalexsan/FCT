package es.example.ale.fct.ui.formularioEmpresa;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import es.example.ale.fct.R;
import es.example.ale.fct.data.RepositoryImpl;
import es.example.ale.fct.data.local.AppDatabase;
import es.example.ale.fct.data.local.EmpresaDao;
import es.example.ale.fct.data.model.Empresa;
import es.example.ale.fct.databinding.FormEmpresaFragmentBinding;
import es.example.ale.fct.utils.ValidationUtils;

public class FormEmpresaFragment extends Fragment {

    private FormEmpresaViewModelFragment mViewModel;
    private FormEmpresaFragmentBinding binding;
    private NavController navController;
    private String idEmpresaArg;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            FormEmpresaFragmentArgs arg = FormEmpresaFragmentArgs.fromBundle(getArguments());
            idEmpresaArg = arg.getIdEmpresa();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FormEmpresaFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        EmpresaDao empresaDao = AppDatabase.getInstance(getContext()).empresaDao();
        RepositoryImpl repository = new RepositoryImpl(empresaDao);

        mViewModel = ViewModelProviders.of(this, new FormEmpresaFragmentViewModelFactory(repository)).get(FormEmpresaViewModelFragment.class);
        initViews();
    }

    private void initViews() {
        if(!TextUtils.equals(idEmpresaArg,null)){
            fillDatas();
        }
        binding.fab.setOnClickListener(v -> validar());
    }

    private void fillDatas() {
        mViewModel.getSelectedEmpresa(Long.parseLong(idEmpresaArg)).observe(this, empresa -> {
            binding.txtName.setText(empresa.getNombre());
            binding.txtTelefono.setText(String.valueOf(empresa.getTelf()));
            binding.txtCif.setText(empresa.getCif());
            binding.txtDireccion.setText(empresa.getDireccion());
            binding.txtLogo.setText(empresa.getLogoURL());
            binding.txtNombreContacto.setText(empresa.getNombreContacto());
        });
    }

    private void validar() {
        boolean nombreValido = request(binding.txtName);
        boolean telfValido = request(binding.txtTelefono);
        boolean cifValido = request(binding.txtCif);
        boolean direcValida = request(binding.txtDireccion);
        boolean logoValido = request(binding.txtLogo);
        boolean contactoValido = request(binding.txtNombreContacto);

        if(nombreValido && telfValido && cifValido && direcValida && logoValido && contactoValido)
            agregarEmpresa();
    }

    private void agregarEmpresa() {
        Empresa empresa = new Empresa(binding.txtName.getText().toString(),binding.txtCif.getText().toString(),binding.txtDireccion.getText().toString()
                ,Integer.parseInt(binding.txtTelefono.getText().toString()),binding.txtLogo.getText().toString(),binding.txtNombreContacto.getText().toString());

        if(!TextUtils.equals(idEmpresaArg,null)){
            empresa.setId(Long.parseLong(idEmpresaArg));
            mViewModel.updateEmpresa(empresa);
        }
        else
            mViewModel.insertEmpresa(empresa);

        navController.popBackStack();
    }

    private boolean request(TextInputEditText text) {

        if (text.getId() == binding.txtTelefono.getId()) {
            if (!ValidationUtils.isValidPhone(binding.txtTelefono.getText().toString())) {
                text.setError(getText(R.string.invalidNumber));
                return false;
            }
        } else if (text.getId() == binding.txtLogo.getId()) {
            if (!ValidationUtils.isValidUrl(binding.txtLogo.getText().toString())) {
                text.setError(getText(R.string.invalidURL));
                return false;
            }
        } else if (TextUtils.isEmpty(text.getText().toString())) {
            text.setError(getText(R.string.requiredCampo));
            return false;
        }

        return true;
    }

}
