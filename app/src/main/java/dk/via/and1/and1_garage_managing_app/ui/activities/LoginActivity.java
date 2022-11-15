package dk.via.and1.and1_garage_managing_app.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import dk.via.and1.and1_garage_managing_app.R;

public class LoginActivity extends AppCompatActivity
{
    TextInputLayout email, password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailInputLayout);
        password = findViewById(R.id.passwordPlateInputLayout);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signUpClick(View v)
    {
        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
        finish();
    }

    public void signInClick(View v)
    {
        String emailTemp = email.getEditText().getText().toString();
        String passwordTemp = password.getEditText().getText().toString();

        mAuth.signInWithEmailAndPassword(emailTemp,passwordTemp).addOnCompleteListener(task ->
        {
            if (task.isSuccessful())
            {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
            else
            {
                Toast.makeText(this, "COULD NOT LOG IN", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
