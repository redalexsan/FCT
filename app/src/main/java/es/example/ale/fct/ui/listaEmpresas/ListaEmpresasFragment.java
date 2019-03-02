package es.example.ale.fct.ui.listaEmpresas;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.example.ale.fct.R;
import es.example.ale.fct.data.RepositoryImpl;
import es.example.ale.fct.data.local.AppDatabase;
import es.example.ale.fct.data.local.EmpresaDao;
import es.example.ale.fct.databinding.FragmentListaEmpresasBinding;
import es.example.ale.fct.onToolbarChange;
import es.example.ale.fct.MainActivityViewModel;


public class ListaEmpresasFragment extends Fragment {


    private NavController navController;
    private onToolbarChange toolbarChange;
    private FragmentListaEmpresasBinding binding;
    private ListaEmpresasFragmentViewModel viewModel;
    private MainActivityViewModel viewModelAcivity;
    private ListaEmpresasFragmentAdapter listAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        toolbarChange = (onToolbarChange) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListaEmpresasBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        EmpresaDao empresaDao = AppDatabase.getInstance(getContext()).empresaDao();
        RepositoryImpl repository = new RepositoryImpl(empresaDao);
        viewModelAcivity = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        viewModel = ViewModelProviders.of(this,new ListaEmpresasFragmentViewModelFactory(repository)).get(ListaEmpresasFragmentViewModel.class);
        setupToolbar();
        initViews();
    }

    private void initViews() {
        RecyclerView lstEmpresas = binding.lstEmpresas;
        listAdapter = new ListaEmpresasFragmentAdapter(navController,viewModelAcivity);
        lstEmpresas.setHasFixedSize(true);
        lstEmpresas.setLayoutManager(new LinearLayoutManager(requireContext()));
        lstEmpresas.setItemAnimator(new DefaultItemAnimator());
        lstEmpresas.setAdapter(listAdapter);

        viewModel.getEmpresas().observe(this, empresas -> listAdapter.submitList(empresas));
    }

    private void setupToolbar() {
        Toolbar toolbar = ViewCompat.requireViewById(requireView(), R.id.toolbar);
        toolbarChange.setUpToolbarFragment(toolbar);
    }

}
