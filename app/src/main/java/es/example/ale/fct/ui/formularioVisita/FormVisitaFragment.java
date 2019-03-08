package es.example.ale.fct.ui.formularioVisita;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import es.example.ale.fct.MainActivityViewModel;
import es.example.ale.fct.R;
import es.example.ale.fct.data.RepositoryImpl;
import es.example.ale.fct.data.local.AppDatabase;
import es.example.ale.fct.data.local.VisitaDao;
import es.example.ale.fct.data.model.Visita;
import es.example.ale.fct.databinding.FragmentFormVisitaBinding;
import es.example.ale.fct.onToolbarChange;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormVisitaFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private FragmentFormVisitaBinding binding;
    private Calendar calendar;
    private boolean isHoraInicio;
    private int horaInicio=0,horaFin=0;
    private FormVisitaFragmentViewModel viewModel;
    private MainActivityViewModel mainActivityViewModel;
    private NavController navController;
    private String idVisita;
    private onToolbarChange toolbarChange;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        toolbarChange = (onToolbarChange) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            FormVisitaFragmentArgs args = FormVisitaFragmentArgs.fromBundle(getArguments());
            idVisita = args.getIdVisita();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormVisitaBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        mainActivityViewModel.setNombreAlumnoSeleccionado(null);
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        VisitaDao visitaDao = AppDatabase.getInstance(getContext()).visitaDao();
        RepositoryImpl repository = new RepositoryImpl(visitaDao);

        mainActivityViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        viewModel = ViewModelProviders.of(this,new FormVisitaFragmentViewModelFactory(repository)).get(FormVisitaFragmentViewModel.class);
        calendar = Calendar.getInstance();
        initDialogs();
        initViews();
        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = ViewCompat.requireViewById(requireView(), R.id.toolbar);
        toolbarChange.setUpToolbarFragment(toolbar);
    }

    private void initViews() {
        binding.fab.setOnClickListener(v -> validar());
        if(!TextUtils.equals(idVisita,null))
            fillDatas();

        binding.txtNombreAlumno.setOnClickListener(v -> navController.navigate(R.id.listaAlumnosFragment));
        mainActivityViewModel.getNombreAlumnoSeleccionado().observe(this,nombre -> binding.txtNombreAlumno.setText(nombre));
    }

    private void fillDatas() {
        viewModel.getSelectedVisita(Integer.parseInt(idVisita)).observe(this, visita -> {
            binding.txtNombreAlumno.setText(visita.getNombreAlumno());
            binding.txtDia.setText(visita.getDia());
            binding.txtHoraInicio.setText(visita.getHoraInicio());
            binding.txtHoraFin.setText(visita.getHoraFin());
            binding.txtObservaciones.setText(visita.getObservaciones());
        });
    }

    private void validar() {
        boolean alumnoValido = request(binding.txtNombreAlumno);
        boolean diaValido = request(binding.txtDia);
        boolean horaInicioValida = request(binding.txtHoraInicio);
        boolean observacionValida = request(binding.txtObservaciones);

        if(alumnoValido && diaValido && horaInicioValida && observacionValida){
            insertVisita();
        }

    }

    private void insertVisita() {
        Visita visita = new Visita(binding.txtNombreAlumno.getText().toString(),binding.txtObservaciones.getText().toString(),binding.txtHoraInicio.getText().toString(),
                binding.txtHoraFin.getText().toString(),binding.txtDia.getText().toString());

        if(!TextUtils.equals(idVisita,null)){
            visita.setId(Integer.parseInt(idVisita));
            viewModel.updateVisita(visita);
        }
        else
            viewModel.insertVisita(visita);
        navController.popBackStack();
    }

    private boolean request(TextInputEditText text){
        if(text.getId() == binding.txtHoraInicio.getId()){
            if(!TextUtils.isEmpty(text.getText().toString())) {
                if (horaInicio > horaFin) {
                    binding.txtHoraFin.setError(getText(R.string.horaMayorError));
                    return false;
                }
            }else {
                text.setError(getText(R.string.requiredCampo));
                return false;
            }
        }
        else if(TextUtils.isEmpty(text.getText().toString())){
            text.setError(getText(R.string.requiredCampo));
            return false;
        }
        return true;
    }


    private void initDialogs() {
        binding.txtDia.setOnClickListener(v -> showDatePickerDialog());
        binding.txtHoraInicio.setOnClickListener(v -> {
            isHoraInicio = true;
            showTimePickerDialog();
        });
        binding.txtHoraFin.setOnClickListener(v -> {
            isHoraInicio = false;
            showTimePickerDialog();
        });
    }

    private void showTimePickerDialog() {
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),this,hh,mm,true);
        timePickerDialog.show();
    }

    private void showDatePickerDialog() {
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(),this,yy,mm,dd);
        datePicker.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = String.format("%02d",dayOfMonth) + "/" + String.format("%02d",(month+1));
        binding.txtDia.setText(date);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hour = String.format("%d:%d",hourOfDay,minute);
        if(isHoraInicio) {
            horaInicio = hourOfDay;
            binding.txtHoraInicio.setText(hour);
            if(TextUtils.isEmpty(binding.txtHoraFin.getText().toString())) {
                horaFin = hourOfDay + 1;
                binding.txtHoraFin.setText(String.format("%d:%d", hourOfDay + 1, minute));
            }
        }
        else {
            horaFin = hourOfDay;
            binding.txtHoraFin.setText(hour);
        }
    }
}
