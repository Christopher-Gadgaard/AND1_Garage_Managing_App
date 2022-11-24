package dk.via.and1.and1_garage_managing_app.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;


import dk.via.and1.and1_garage_managing_app.data.user.User;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;

public class MainActivityViewModel extends AndroidViewModel
{
    private final UserRepository userRepository;

    public MainActivityViewModel(Application app)
    {
        super(app);
        userRepository = UserRepository.getInstance();
    }

    public LiveData<FirebaseUser> getCurrentFirebaseUser()
    {
      return userRepository.getCurrentFirebaseUser();
    }

    public LiveData<User> getUser()
    {
        return userRepository.getUser();
    }

    public void logout()
    {
        userRepository.logout();
    }
}
