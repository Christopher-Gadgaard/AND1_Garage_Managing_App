package dk.via.and1.and1_garage_managing_app.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import dk.via.and1.and1_garage_managing_app.R;
import dk.via.and1.and1_garage_managing_app.ui.viewmodels.LoginActivityViewModel;

public class LoginActivity extends AppCompatActivity
{
    LoginActivityViewModel viewModel;
    EditText email, password;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        viewModel.init();

        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailTextview);
        password = findViewById(R.id.passwordTextView);

        fAuth = FirebaseAuth.getInstance();

        checkIfSignedIn();
    }

    public void signUpClick(View v)
    {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

    public void signInClick(View v)
    {
        String emailTemp = email.getText().toString();
        String passwordTemp = password.getText().toString();

        fAuth.signInWithEmailAndPassword(emailTemp, passwordTemp).addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
            else
            {
                Toast.makeText(this, "COULD NOT LOG IN", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkIfSignedIn()
    {
        if (fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    public void recoverPassword(View view)
    {

        final EditText resetEmail = new EditText(view.getContext());
        final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
        passwordResetDialog.setTitle("Reset Password?");
        passwordResetDialog.setMessage("Enter Your Email, A recovery link will be sent");
        passwordResetDialog.setView(resetEmail);


        passwordResetDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
            String email = resetEmail.getText().toString();
            fAuth.sendPasswordResetEmail(email)
                    .addOnSuccessListener(e -> Toast.makeText(LoginActivity.this,
                                                              "Reset link sent to your email.",
                                                              Toast.LENGTH_LONG).show())
                    .addOnFailureListener(e -> Toast.makeText(LoginActivity.this,
                                                              "Could not send reset link!" + e.getMessage(),
                                                              Toast.LENGTH_LONG).show());
        });

        passwordResetDialog.setNegativeButton("No", ((dialogInterface, i) -> {

        }));

        passwordResetDialog.create().show();
    }
}
