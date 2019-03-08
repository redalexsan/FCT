package es.example.ale.fct.ui.alumnos;


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
import es.example.ale.fct.data.local.AlumnoDao;
import es.example.ale.fct.data.local.AppDatabase;
import es.example.ale.fct.databinding.FragmentAlumnosBinding;
import es.example.ale.fct.onToolbarChange;

public class AlumnosFragment extends Fragment {

    private NavController navController;
    private onToolbarChange toolbarChange;
    private AlumnosFragmentAdapter listAdapter;
    private AlumnosFragmentViewModel viewModel;
    private FragmentAlumnosBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        toolbarChange = (onToolbarChange) context;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAlumnosBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        AlumnoDao alumnoDao = AppDatabase.getInstance(getContext()).alumnoDao();
        RepositoryImpl repository = new RepositoryImpl(alumnoDao);

        viewModel = ViewModelProviders.of(this,new AlumnosFragmentViewModelFactory(repository)).get(AlumnosFragmentViewModel.class);

        setupViews();
        setupToolbar();
        viewModel.getAlumnos().observe(this, alumnos -> {
            binding.lblEmptyView.setVisibility(alumnos.size() == 0 ? View.VISIBLE : View.INVISIBLE);
            listAdapter.submitList(alumnos);
        });
    }

    private void setupViews() {
        RecyclerView lstAlumnos = binding.lstAlumnos;
        listAdapter = new AlumnosFragmentAdapter(navController);
        lstAlumnos.setHasFixedSize(true);
        lstAlumnos.setLayoutManager(new LinearLayoutManager(requireContext()));
        lstAlumnos.setItemAnimator(new DefaultItemAnimator());
        lstAlumnos.setAdapter(listAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteAlumno(listAdapter.getItem(viewHolder.getAdapterPosition()));
            }
        });
        itemTouchHelper.attachToRecyclerView(lstAlumnos);
        binding.fab.setOnClickListener(v -> navController.navigate(R.id.destAlumnosToForm));
    }

    private void setupToolbar() {
        Toolbar toolbar = binding.toolbar;
        toolbarChange.setUpToolbarFragment(toolbar);
    }

}
