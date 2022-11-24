package dk.via.and1.and1_garage_managing_app.ui.my.account;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import dk.via.and1.and1_garage_managing_app.data.user.User;
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
        String userId = userRepository.getCurrentFirebaseUser().getValue().getUid();
        userRepository.init(userId);
    }

    public LiveData<User> getUser()
    {
        return userRepository.getUser();
    }
}