package dk.via.and1.and1_garage_managing_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;

public class StartUpActivityViewModel extends AndroidViewModel {
    UserRepository userRepository;

    public StartUpActivityViewModel(@NonNull Application application)
    {
        super(application);
        userRepository = UserRepository.getInstance();
        userRepository.initAuth();
    }

    public LiveData<FirebaseUser> getUserAuthLiveData()
    {
        return userRepository.getUserAuthLiveData();
    }
}
