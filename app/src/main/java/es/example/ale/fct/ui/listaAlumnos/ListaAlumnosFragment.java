package es.example.ale.fct.ui.listaAlumnos;


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

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.example.ale.fct.MainActivityViewModel;
import es.example.ale.fct.R;
import es.example.ale.fct.data.RepositoryImpl;
import es.example.ale.fct.data.local.AlumnoDao;
import es.example.ale.fct.data.local.AppDatabase;
import es.example.ale.fct.databinding.FragmentListaAlumnosBinding;
import es.example.ale.fct.onToolbarChange;


public class ListaAlumnosFragment extends Fragment {


    private FragmentListaAlumnosBinding binding;
    private NavController navController;
    private onToolbarChange toolbarChange;
    private  ListAlumnosFragmentViewModel viewModel;
    private MainActivityViewModel mainActivityViewModel;
    private ListAlumnoFragmentAdapter listAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        toolbarChange = (onToolbarChange) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListaAlumnosBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        mainActivityViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        AlumnoDao alumnoDao = AppDatabase.getInstance(getContext()).alumnoDao();
        RepositoryImpl repository = new RepositoryImpl(alumnoDao);
        viewModel = ViewModelProviders.of(this,new ListaAluimnosFragmentViewModelFactory(repository)).get(ListAlumnosFragmentViewModel.class);
        setupToolbar();
        initViews();
    }

    private void initViews() {
        RecyclerView lstEmpresas = binding.lstAlumnos;
        listAdapter = new ListAlumnoFragmentAdapter(navController,mainActivityViewModel);
        lstEmpresas.setHasFixedSize(true);
        lstEmpresas.setLayoutManager(new LinearLayoutManager(requireContext()));
        lstEmpresas.setItemAnimator(new DefaultItemAnimator());
        lstEmpresas.setAdapter(listAdapter);

        viewModel.getAlumnos().observe(this, alumnos -> listAdapter.submitList(alumnos));
    }

    private void setupToolbar() {
        Toolbar toolbar = ViewCompat.requireViewById(requireView(), R.id.toolbar);
        toolbarChange.setUpToolbarFragment(toolbar);
    }
}
