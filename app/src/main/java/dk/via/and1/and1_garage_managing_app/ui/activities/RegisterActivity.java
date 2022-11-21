package dk.via.and1.and1_garage_managing_app.ui.activities;

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

import dk.via.and1.and1_garage_managing_app.R;
import dk.via.and1.and1_garage_managing_app.data.user.User;
import dk.via.and1.and1_garage_managing_app.ui.viewmodels.RegisterActivityViewModel;

public class RegisterActivity extends AppCompatActivity {
    RegisterActivityViewModel viewModel;
    EditText editTextFirstName, editTextLastName, editTextEmail, editTextPhoneNo, editTextLicensePlate, editTextPassword, editTextConfirmPassword;
    ProgressBar progressBar;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(RegisterActivityViewModel.class);
        viewModel.init();

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
    }

    public void register(View v)
    {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNo = editTextPhoneNo.getText().toString().trim();
        String licensePlate = editTextLicensePlate.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

      /*  if (firstName.isEmpty()) //TODO SHOULD THIS BE EXTRACTED ?? ASK KASPER
        {
            editTextFirstName.setError("Required*");
            editTextFirstName.requestFocus();
            return;
        }
        if (lastName.isEmpty()) {
            editTextLastName.setError("Required*");
            editTextFirstName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Required*");
            editTextFirstName.requestFocus();
            return;
        }
        if (phoneNo.isEmpty()) {
            editTextPhoneNo.setError("Required*");
            editTextFirstName.requestFocus();
            return;
        }
        if (licensePlate.isEmpty()) {
            editTextLicensePlate.setError("Required*");
            editTextFirstName.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Required*");
            editTextFirstName.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Password length 6-20 characters"); //TODO IMPLEMENT PASSWORD REGEX
            editTextFirstName.requestFocus();
            return;
        }
        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("Passwords Must Match");
            editTextFirstName.requestFocus();
            return;
        }*/

        progressBar.setVisibility(View.VISIBLE);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(layout.getWindowToken(),0);


       // User user = new User(firstName, lastName, phoneNo, email, licensePlate, false);

        User user = new User("joe", "doe", "phoneNo", "123456@1.dk", "11111", false);

        viewModel.registerUser(user,"1234567"); //TODO CHANGE TO PASSWORD

        if (viewModel.getCurrentUser() != null)
        {
            Toast.makeText(this, "Register was successful", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Register was unsuccessful", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }

        //Register the user in firebase
    /*    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> { //TODO ASK KASPER SHOULD THIS BE IN THE userRepo
            if (task.isSuccessful()) {
                User user = new User(firstName, lastName, phoneNo, email, licensePlate, false);

                FirebaseDatabase.getInstance("https://and1-garage-managing-app-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(task1 -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Register was successful", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(this, "Registering of userinfo was unsuccessful", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            } else {
                Toast.makeText(this, "Register of account was unsuccessful", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
            return;
        });*/


    }


}