package dk.via.and1.and1_garage_managing_app.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;

public class LoginActivityViewModel extends AndroidViewModel
{
    UserRepository userRepository;

    public LoginActivityViewModel(@NonNull Application app)
    {
        super(app);
        userRepository = UserRepository.getInstance();
    }

    public void init()
    {
        // TODO IMPLEMENT
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }


}
