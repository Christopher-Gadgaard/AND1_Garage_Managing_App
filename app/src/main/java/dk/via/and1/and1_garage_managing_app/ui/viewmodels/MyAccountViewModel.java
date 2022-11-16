package dk.via.and1.and1_garage_managing_app.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import dk.via.and1.and1_garage_managing_app.data.user.UserInfo;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;

public class MyAccountViewModel extends AndroidViewModel
{
    private final UserRepository userRepository;

    public MyAccountViewModel(@NonNull Application app)
    {
        super(app);
        userRepository = UserRepository.getInstance();
    }

    public void init()
    {
        //TODO do or remove
    }

    public LiveData<UserInfo> getUserInfo()
    {
        userRepository.init();
        return userRepository.getUserInfo();
    }
}