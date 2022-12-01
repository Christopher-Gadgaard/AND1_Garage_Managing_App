package dk.via.and1.and1_garage_managing_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import dk.via.and1.and1_garage_managing_app.ui.login.LoginActivity;
import dk.via.and1.and1_garage_managing_app.ui.main.MainActivity;

public class StartUpActivity extends AppCompatActivity {

    private StartUpActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        viewModel = new ViewModelProvider(this).get(StartUpActivityViewModel.class);
        checkIfSignedIn();
    }

    private void checkIfSignedIn()
    {
        viewModel.getUserAuthLiveData().observe(this, user ->
        {
            if (user != null) {
                if (!user.getDisplayName().isEmpty())
                {
                    Toast.makeText(this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                }
                goToMainActivity();
            } else {
                goToLoginActivity();
            }
        });
    }

    public void goToLoginActivity()
    {
        startActivity(new Intent(this, LoginActivity.class));
    }
    public void goToMainActivity()
    {
        startActivity(new Intent(this, MainActivity.class));
    }
}