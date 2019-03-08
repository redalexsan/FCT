package es.example.ale.fct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements onToolbarChange{

    private MainActivityViewModel viewModel;
    private NavController navController;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        navController = Navigation.findNavController(this,R.id.navHostFragment);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        viewModel.setDefaultPage(preferences.getString(getString(R.string.prefInitalPageKey), getString(R.string.proximaVisita)));
        viewModel.setDaysPerMeeting(preferences.getInt(getString(R.string.daysKey),15));
        setupNavigationDrawer();
        if(savedInstanceState == null)
           setUpNavigationGraph();
    }


    private void setUpNavigationGraph() {
        int startDestination = 0;
        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        NavController navController = navHost.getNavController();
        NavInflater navInflater = navController.getNavInflater();
        NavGraph navGraph = navInflater.inflate(R.navigation.main_navigation);

        switch (viewModel.getHomePage()){
            case "Empresas":
                startDestination = R.id.empresasFragment;
                break;
            case "Alumnos":
                startDestination = R.id.alumnosFragment;
                break;
            case "Visitas Realizadas":
                startDestination = R.id.visitasFragment;
                break;
            case "Proximas Visitas":
                startDestination = R.id.visitasProximasFragment;
                break;
        }
        navGraph.setStartDestination(startDestination);
        navController.setGraph(navGraph);

    }

    private void setupNavigationDrawer() {
        NavigationView navigationView = ActivityCompat.requireViewById(this, R.id.navigationView);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void setUpToolbar(Toolbar toolbar){
        drawerLayout = ActivityCompat.requireViewById(this,R.id.drawerLayout);
        setSupportActionBar(toolbar);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.alumnosFragment,R.id.visitasFragment,R.id.visitasProximasFragment,R.id.empresasFragment).setDrawerLayout(drawerLayout).build();
        NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration);
    }

    @Override
    public void setUpToolbarFragment(Toolbar toolbar) {
        setUpToolbar(toolbar);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }
}
