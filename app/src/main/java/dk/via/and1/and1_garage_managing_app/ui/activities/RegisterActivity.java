package dk.via.and1.and1_garage_managing_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dk.via.and1.and1_garage_managing_app.R;
import dk.via.and1.and1_garage_managing_app.data.user.UserInfo;
import dk.via.and1.and1_garage_managing_app.ui.viewmodels.RegisterActivityViewModel;

public class RegisterActivity extends AppCompatActivity
{
    RegisterActivityViewModel viewModel;
    TextInputLayout firstName, lastName, email, phoneNo, licensePlate, password, confirmPassword;
    FirebaseAuth fAuth;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(RegisterActivityViewModel.class);
        viewModel.init();

        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.firstNameInputLayout);
        lastName = findViewById(R.id.lastNameInputLayout);
        email = findViewById(R.id.emailInputLayout);
        phoneNo = findViewById(R.id.phoneInputLayout);
        licensePlate = findViewById(R.id.licensePlateInputLayout);
        password = findViewById(R.id.passwordPlateInputLayout);
        confirmPassword = findViewById(R.id.passwordConfirmPlateInputLayout);

        fAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
    }

    public void register(View v)
    {
        String firstNameTemp = firstName.getEditText().getText().toString().trim(); //TODO SHOULD THESE BE OBJECT REQUIRED NOT NULL, change in other places if so
        String lastNameTemp = lastName.getEditText().getText().toString().trim();
        String emailTemp = email.getEditText().getText().toString().trim();
        String phoneNoTemp = phoneNo.getEditText().getText().toString().trim();
        String licensePlateTemp = licensePlate.getEditText().getText().toString().trim();
        String passwordTemp = password.getEditText().getText().toString().trim();
        String confirmPasswordTemp = confirmPassword.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(firstNameTemp)) //TODO SHOULD THIS BE EXTRACTED ?? ASK KASPER
        {
            firstName.setError("Required*");
            return;
        }
        if (TextUtils.isEmpty(lastNameTemp))
        {
            lastName.setError("Required*");
            return;
        }
        if (TextUtils.isEmpty(emailTemp))
        {
            email.setError("Required*");
            return;
        }
        if (TextUtils.isEmpty(phoneNoTemp))
        {
            phoneNo.setError("Required*");
            return;
        }
        if (TextUtils.isEmpty(licensePlateTemp))
        {
            licensePlate.setError("Required*");
            return;
        }
        if (TextUtils.isEmpty(passwordTemp))
        {
            password.setError("Required*");
            return;
        }
        if(passwordTemp.length()<6)
        {
            password.setError("Password length 6-20 characters"); //TODO IMPLEMENT PASSWORD REGEX
            return;
        }
        if (!passwordTemp.equals(confirmPasswordTemp))
        {
            confirmPassword.setError("Passwords Must Match");
            return;
        }


        //Register the user in firebase
        fAuth.createUserWithEmailAndPassword(emailTemp,passwordTemp).addOnCompleteListener(task ->
        {
            if (task.isSuccessful())
            {
                Toast.makeText(this, "REGISTERED", Toast.LENGTH_SHORT).show();

              //  startActivity(new Intent(getApplicationContext(), MainActivity.class));//TODO ASK KASPER ABOUT THIS, is this the right way to start.
               // finish();
            }
            else
                Toast.makeText(this, "COULD NOT REGISTER", Toast.LENGTH_SHORT).show();
            return;
        });

        UserInfo userInfo = new UserInfo(); //TODO FIX THIS
        userInfo.setFirstName("TEST");

        myRef.child("users").child(viewModel.getCurrentUser().getValue().getUid()).setValue(userInfo);
    }




}