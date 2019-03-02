package es.example.ale.fct.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import es.example.ale.fct.MainActivityViewModel;
import es.example.ale.fct.R;
import es.example.ale.fct.onToolbarChange;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private NavController navController;
    private onToolbarChange toolbarChange;
    private MainActivityViewModel viewModel;

    private final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = this;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences,rootKey);
    }
    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override
    public void onPause() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        super.onPause();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        viewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        setUpToolBar();
    }


    private void setUpToolBar() {
        //Peta
//        ((AppCompatActivity) requireActivity()).setSupportActionBar(requireActivity().findViewById(R.id.toolbarPrefence));
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        viewModel.setDefaultPage(sharedPreferences.getString(getString(R.string.prefInitalPageKey),getString(R.string.proximaVisita)));
        viewModel.setDaysPerMeeting(sharedPreferences.getInt(getString(R.string.daysKey),15));
    }
}
