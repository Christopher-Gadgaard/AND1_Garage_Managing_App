package dk.via.and1.and1_garage_managing_app.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;


import dk.via.and1.and1_garage_managing_app.data.garage.GarageRepository;
import dk.via.and1.and1_garage_managing_app.data.nav.NavRepository;
import dk.via.and1.and1_garage_managing_app.data.nav.NavResponse;
import dk.via.and1.and1_garage_managing_app.data.user.User;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;

public class MainActivityViewModel extends AndroidViewModel
{
    private final UserRepository userRepository;



    public MainActivityViewModel(Application app)
    {
        super(app);
        userRepository = UserRepository.getInstance();
        userRepository.initAuth();
        userRepository.initDatabase();
    }

    public LiveData<FirebaseUser> getUserAuthLiveData()
    {
      return userRepository.getUserAuthLiveData();
    }

    public LiveData<User> getUser()
    {
        return userRepository.getUserLiveData();
    }

    public void logout()
    {
        userRepository.logout();
    }


}
