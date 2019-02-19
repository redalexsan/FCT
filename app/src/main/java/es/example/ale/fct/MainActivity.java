package es.example.ale.fct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements onToolbarChange{

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this,R.id.navHostFragment);
        setupNavigationDrawer();
    }

    private void setupNavigationDrawer() {
        NavigationView navigationView = ActivityCompat.requireViewById(this, R.id.navigationView);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void setUpToolbar(Toolbar toolbar){
        DrawerLayout drawerLayout = ActivityCompat.requireViewById(this,R.id.drawerLayout);
        setSupportActionBar(toolbar);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.alumnosFragment,R.id.visitasFragment,R.id.empresasFragment).setDrawerLayout(drawerLayout).build();

        NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration);
    }

    @Override
    public void setUpToolbarFragment(Toolbar toolbar) {
        setUpToolbar(toolbar);
    }
}
