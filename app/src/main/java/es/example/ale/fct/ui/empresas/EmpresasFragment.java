
package es.example.ale.fct.ui.empresas;


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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.example.ale.fct.MainActivity;
import es.example.ale.fct.R;
import es.example.ale.fct.data.RepositoryImpl;
import es.example.ale.fct.data.local.AppDatabase;
import es.example.ale.fct.data.local.EmpresaDao;
import es.example.ale.fct.databinding.FragmentEmpresasBinding;
import es.example.ale.fct.onToolbarChange;


public class EmpresasFragment extends Fragment {


    private NavController navController;
    private onToolbarChange toolbarChange;
    private FragmentEmpresasBinding binding;
    private EmpresaFragmentViewModel viewModel;
    private EmpresasFragmentAdapter listAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        toolbarChange = (onToolbarChange) context;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEmpresasBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        EmpresaDao empresaDao = AppDatabase.getInstance(getContext()).empresaDao();
        RepositoryImpl repository = new RepositoryImpl(empresaDao);

        viewModel = ViewModelProviders.of(this,new EmpresaFragmentViewModelFactory(repository)).get(EmpresaFragmentViewModel.class);
        viewModel.getEmpresas().observe(this,empresas -> {
            binding.lblEmptyView.setVisibility(empresas.size() == 0 ? View.VISIBLE : View.INVISIBLE);
            listAdapter.submitList(empresas);
        });

        initViews();
        setupToolbar();
    }

    private void initViews() {
        RecyclerView lstEmpresas = binding.lstEmpresas;
        listAdapter = new EmpresasFragmentAdapter(navController);
        lstEmpresas.setHasFixedSize(true);
        lstEmpresas.setLayoutManager(new LinearLayoutManager(requireContext()));
        lstEmpresas.setItemAnimator(new DefaultItemAnimator());
        lstEmpresas.setAdapter(listAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteEmpresa(listAdapter.getItem(viewHolder.getAdapterPosition()));
            }
        });
        itemTouchHelper.attachToRecyclerView(lstEmpresas);
        binding.fab.setOnClickListener(v -> navController.navigate(R.id.action_empresasFragment_to_formEmpresaFragment));
    }

    private void setupToolbar() {
        Toolbar toolbar = binding.toolbar;
        toolbarChange.setUpToolbarFragment(toolbar);
    }

}

