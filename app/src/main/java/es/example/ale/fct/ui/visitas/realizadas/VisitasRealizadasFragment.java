package es.example.ale.fct.ui.visitas.realizadas;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.example.ale.fct.R;
import es.example.ale.fct.data.RepositoryImpl;
import es.example.ale.fct.data.local.AppDatabase;
import es.example.ale.fct.data.local.VisitaDao;
import es.example.ale.fct.databinding.FragmentVisitasBinding;
import es.example.ale.fct.onToolbarChange;


public class VisitasRealizadasFragment extends Fragment {

    private NavController navController;
    private onToolbarChange toolbarChange;
    private FragmentVisitasBinding binding;
    private VisitasRealizadasFragmentViewModel viewModel;
    private VisitasRealizadasFragmentAdapter listAdapter;

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
        binding = FragmentVisitasBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        VisitaDao visitaDao = AppDatabase.getInstance(getContext()).visitaDao();
        RepositoryImpl repository = new RepositoryImpl(visitaDao);

        viewModel = ViewModelProviders.of(this,new VisitasRealizadasFragmentViewModelFactory(repository)).get(VisitasRealizadasFragmentViewModel.class);
        viewModel.getVisitas().observe(this, visitas -> listAdapter.submitList(visitas) );
        initViews();
        setupToolbar();
    }

    private void initViews() {
        binding.fab.setOnClickListener(v -> navController.navigate(R.id.action_visitasFragment_to_formVisitaFragment));

        RecyclerView lstVisitas = binding.lstVisitas;
        listAdapter = new VisitasRealizadasFragmentAdapter(navController);
        lstVisitas.setHasFixedSize(true);
        lstVisitas.setLayoutManager(new LinearLayoutManager(requireContext()));
        lstVisitas.setItemAnimator(new DefaultItemAnimator());
        lstVisitas.setAdapter(listAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteVisita(listAdapter.getItem(viewHolder.getAdapterPosition()));
            }
        });
        itemTouchHelper.attachToRecyclerView(lstVisitas);
    }


    private void setupToolbar() {
        Toolbar toolbar = binding.toolbar;
        toolbarChange.setUpToolbarFragment(toolbar);
    }

}

