package es.example.ale.fct.ui.alumnos;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.example.ale.fct.R;
import es.example.ale.fct.data.RepositoryImpl;
import es.example.ale.fct.data.local.AlumnoDao;
import es.example.ale.fct.data.local.AppDatabase;
import es.example.ale.fct.data.model.Alumno;
import es.example.ale.fct.onToolbarChange;

public class AlumnosFragment extends Fragment {

    private NavController navController;
    private onToolbarChange toolbarChange;
    private AlumnosFragmentAdapter listAdapter;
    private AlumnosFragmentViewModel viewModel;
    private Alumno alumnoPrueba = new Alumno("Alumno de Prueba",12345689,"prueba@gamil.com","2ºDAM");

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        toolbarChange = (onToolbarChange) context;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alumnos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        AlumnoDao alumnoDao = AppDatabase.getInstance(getContext()).alumnoDao();
        RepositoryImpl repository = new RepositoryImpl(alumnoDao);

        viewModel = ViewModelProviders.of(this,new AlumnosFragmentViewModelFactory(repository)).get(AlumnosFragmentViewModel.class);

        setupViews(requireView());
        setupToolbar();
        viewModel.getAlumnos().observe(this, alumnos -> listAdapter.submitList(alumnos));
    }

    private void setupViews(View view) {
        RecyclerView lstAlumnos = ViewCompat.requireViewById(view,R.id.lstAlumnos);
        listAdapter = new AlumnosFragmentAdapter();
        lstAlumnos.setHasFixedSize(true);
        lstAlumnos.setLayoutManager(new LinearLayoutManager(requireContext()));
        lstAlumnos.setItemAnimator(new DefaultItemAnimator());
        lstAlumnos.setAdapter(listAdapter);

        FloatingActionButton fab = ViewCompat.requireViewById(view,R.id.fab);
        fab.setOnClickListener(v -> viewModel.insertAlumno(alumnoPrueba));
        fab.setOnClickListener(v -> navController.navigate(R.id.action_alumnosFragment_to_formAlumnoFragment));
    }

    private void setupToolbar() {
        Toolbar toolbar = ViewCompat.requireViewById(requireView(), R.id.toolbar);
        toolbarChange.setUpToolbarFragment(toolbar);
    }

}
