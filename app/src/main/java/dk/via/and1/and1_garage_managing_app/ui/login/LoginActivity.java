package dk.via.and1.and1_garage_managing_app.ui.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;


import dk.via.and1.and1_garage_managing_app.R;
import dk.via.and1.and1_garage_managing_app.ui.main.MainActivity;
import dk.via.and1.and1_garage_managing_app.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity
{
    LoginActivityViewModel viewModel;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.emailTextview);
        password = findViewById(R.id.passwordTextView);
        viewModel.getResult().observe(this,result-> Toast.makeText(this,result,Toast.LENGTH_LONG).show());
    }

    public void registerClick(View v)
    {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

    public void loginClick(View v)
    {
        String emailTemp = email.getText().toString();
        String passwordTemp = password.getText().toString();

        if (emailTemp.isEmpty())
        {
            email.setError("Required*");
            email.requestFocus();
            return;
        }

        if (passwordTemp.isEmpty())
        {
            password.setError("Required*");
            password.requestFocus();
            return;
        }

        viewModel.login(emailTemp,passwordTemp);
        viewModel.getCurrentFirebaseUser().observe(this, auth -> {
            if (auth != null) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });
    }

    public void recoverPasswordClick(View view) //TODO MOVE TO REPO AND MAKE ALERTDIALOG MATCH THEME
    {
        final EditText resetEmail = new EditText(view.getContext());
        final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
        passwordResetDialog.setTitle("Reset Password?");
        passwordResetDialog.setMessage("Enter Your Email, A recovery link will be sent");
        passwordResetDialog.setView(resetEmail);
        passwordResetDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
            String email = resetEmail.getText().toString();
         viewModel.recoverPassword(email);
        });
        passwordResetDialog.setNegativeButton("No", ((dialogInterface, i) -> {
        }));
        passwordResetDialog.create().show();
    }
}