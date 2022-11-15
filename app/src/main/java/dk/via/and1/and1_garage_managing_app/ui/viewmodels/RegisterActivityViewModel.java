package dk.via.and1.and1_garage_managing_app.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;

public class RegisterActivityViewModel extends AndroidViewModel
{
  private final UserRepository userRepository;

    public RegisterActivityViewModel(@NonNull Application application)
    {
        super(application);
        userRepository = UserRepository.getInstance(application);
    }

    public void init()
    {
        // TODO IMPLEMENT
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public FirebaseAuth getAuth()
    {
       return userRepository.getAuth();
    }

}
