package dk.via.and1.and1_garage_managing_app.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import dk.via.and1.and1_garage_managing_app.R;
import dk.via.and1.and1_garage_managing_app.data.user.User;
import dk.via.and1.and1_garage_managing_app.ui.main.MainActivity;
import dk.via.and1.and1_garage_managing_app.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
    RegisterActivityViewModel viewModel;
    EditText editTextFirstName, editTextLastName, editTextEmail, editTextPhoneNo, editTextLicensePlate, editTextPassword, editTextConfirmPassword;
    TextInputLayout textInputLayoutFirstName, textInputLayoutLastName, textInputLayoutEmail, textInputLayoutPhoneNo, textInputLayoutLicensePlate, textInputLayoutPassword, textInputLayoutConfirmPassword;
    ProgressBar progressBar;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(RegisterActivityViewModel.class);

        setContentView(R.layout.activity_register);
        editTextFirstName = findViewById(R.id.firstNameEditText);
        editTextLastName = findViewById(R.id.lastNameEditText);
        editTextEmail = findViewById(R.id.emailEditText);
        editTextPhoneNo = findViewById(R.id.phoneEditText);
        editTextLicensePlate = findViewById(R.id.licensePlateEditText);
        editTextPassword = findViewById(R.id.passwordEditText);
        editTextConfirmPassword = findViewById(R.id.passwordConfirmEditText);
        progressBar = findViewById(R.id.progressBar);
        layout = findViewById(R.id.constraintLayout);

        textInputLayoutFirstName = findViewById(R.id.firstNameInputLayout);
        textInputLayoutLastName = findViewById(R.id.lastNameInputLayout);
        textInputLayoutEmail = findViewById(R.id.emailInputLayout);
        textInputLayoutPhoneNo = findViewById(R.id.phoneInputLayout);
        textInputLayoutLicensePlate = findViewById(R.id.licensePlateInputLayout);
        textInputLayoutPassword = findViewById(R.id.passwordInputLayout);
        textInputLayoutConfirmPassword = findViewById(R.id.passwordConfirmInputLayout);

    }

    public void registerUserClick(View v)
    {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNo = editTextPhoneNo.getText().toString().trim();
        String licensePlate = editTextLicensePlate.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if (firstName.isEmpty())
        {
            textInputLayoutFirstName.setError("Required*");
            editTextFirstName.requestFocus();
            return;
        }
        if (lastName.isEmpty()) {
            textInputLayoutLastName.setError("Required*");
            editTextFirstName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            textInputLayoutEmail.setError("Required*");
            editTextFirstName.requestFocus();
            return;
        }
        if (phoneNo.isEmpty()) {
            textInputLayoutPhoneNo.setError("Required*");
            editTextFirstName.requestFocus();
            return;
        }
        if (licensePlate.isEmpty()) {
            textInputLayoutLicensePlate.setError("Required*");
            editTextFirstName.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            textInputLayoutPassword.setError("Required*");
            editTextFirstName.requestFocus();
            return;
        }
        if (password.length() < 6) {
            textInputLayoutPassword.setError("Password length 6-20 characters");
            editTextFirstName.requestFocus();
            return;
        }
        if (!password.equals(confirmPassword)) {
            textInputLayoutConfirmPassword.setError("Passwords Must Match");
            editTextFirstName.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(layout.getWindowToken(), 0);

         User user = new User(firstName, lastName, phoneNo, email, licensePlate, false);

        viewModel.registerUser(user, password);

        viewModel.getRegisterResult().observe(this, result -> {

            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

            viewModel.getCurrentFirebaseUser().observe(this, auth -> {
                if (auth != null) {
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
            });
        });
    }

    public void backClick(View view)
    {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}