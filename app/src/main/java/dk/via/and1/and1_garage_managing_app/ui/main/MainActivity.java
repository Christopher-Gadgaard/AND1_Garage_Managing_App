package dk.via.and1.and1_garage_managing_app.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import dk.via.and1.and1_garage_managing_app.R;
import dk.via.and1.and1_garage_managing_app.databinding.ActivityMainBinding;
import dk.via.and1.and1_garage_managing_app.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private NavController navController;

    private MainActivityViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        checkIfSignedIn();
        setContentView(binding.getRoot());
        setupNavigation();
    }


    private void setupNavigation()
    {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        setSupportActionBar(binding.toolbar);

        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    public void goToLoginActivity()
    {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void checkIfSignedIn()
    {
        viewModel.getCurrentFirebaseUser().observe(this, user ->
        {
            if (user != null) {
                Toast.makeText(this, "Welcome" + user.getEmail(), Toast.LENGTH_SHORT).show();
            } else {
                goToLoginActivity();
            }
        });
    }
}