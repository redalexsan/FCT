package es.example.ale.fct.ui.visitas.realizadas;


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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import es.example.ale.fct.R;
import es.example.ale.fct.onToolbarChange;


public class VisitasRealizadasFragment extends Fragment {

    private NavController navController;
    private onToolbarChange toolbarChange;
    public VisitasRealizadasFragment() {
    }

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
        return inflater.inflate(R.layout.fragment_visitas, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        setupToolbar();
    }



    private void setupToolbar() {
        Toolbar toolbar = ViewCompat.requireViewById(requireView(), R.id.toolbar);
        toolbarChange.setUpToolbarFragment(toolbar);
    }

}

