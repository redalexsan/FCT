package es.example.ale.fct.ui.visitas.proximas;


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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.example.ale.fct.MainActivityViewModel;
import es.example.ale.fct.R;
import es.example.ale.fct.data.RepositoryImpl;
import es.example.ale.fct.data.local.AlumnoDao;
import es.example.ale.fct.data.local.AppDatabase;
import es.example.ale.fct.data.local.VisitaDao;
import es.example.ale.fct.data.model.Alumno;
import es.example.ale.fct.data.model.Visita;
import es.example.ale.fct.databinding.FragmentProximasVisitasBinding;
import es.example.ale.fct.databinding.FragmentVisitasBinding;
import es.example.ale.fct.onToolbarChange;
import es.example.ale.fct.ui.visitas.realizadas.VisitasRealizadasFragmentAdapter;


public class ProximasVisitasFragment extends Fragment {

    private NavController navController;
    private onToolbarChange toolbarChange;
    private FragmentProximasVisitasBinding binding;
    private ProximasVisitasFragmentViewModel viewModel;
    private ProximasVisitasFragmentAdapter listAdapter;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        toolbarChange = (onToolbarChange) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProximasVisitasBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        mainActivityViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        AlumnoDao alumnoDao = AppDatabase.getInstance(getContext()).alumnoDao();
        VisitaDao visitaDao = AppDatabase.getInstance(getContext()).visitaDao();
        RepositoryImpl repository = new RepositoryImpl(alumnoDao,visitaDao);

        viewModel = ViewModelProviders.of(this,new ProximasVisitasFragmentViewModelFactory(repository)).get(ProximasVisitasFragmentViewModel.class);
        initViews();
        setupToolbar();
    }

    private void initViews() {
        viewModel.getAlumnos().observe(this,alumnos -> {
            for(Alumno alumno: alumnos)
                getUltimaVisita(alumno);
        });
        setUpRecyclerView();
        viewModel.getProximasVisitas().observe(this,visitas ->{
            if(visitas.size() != 0)
                listAdapter.submitList(visitas);
        });
    }

    private void setUpRecyclerView() {
        RecyclerView lstVisitas = binding.lstVisitas;
        listAdapter = new ProximasVisitasFragmentAdapter();
        lstVisitas.setHasFixedSize(true);
        lstVisitas.setLayoutManager(new LinearLayoutManager(requireContext()));
        lstVisitas.setItemAnimator(new DefaultItemAnimator());
        lstVisitas.setAdapter(listAdapter);
    }

    private void getUltimaVisita(Alumno alumno) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM");

        viewModel.getUltimaVisita(alumno.getNombre()).observe(this,visita -> {
            String nuevaFecha = "";
            if(visita == null)
                nuevaFecha = "Cuanto antes";
            else {
                try {
                    Date fecha = formato.parse(visita.getDia());
                    calendar.setTime(fecha);
                    calendar.add(Calendar.DAY_OF_MONTH, mainActivityViewModel.getDaysPerMeeting());

                    nuevaFecha = formato.format(calendar.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            Visita visitaProxima = new Visita(alumno.getNombre(),nuevaFecha);
            viewModel.setProximaVisita(visitaProxima);
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = ViewCompat.requireViewById(requireView(), R.id.toolbar);
        toolbarChange.setUpToolbarFragment(toolbar);
    }

}
