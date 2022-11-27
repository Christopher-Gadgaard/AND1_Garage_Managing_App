package dk.via.and1.and1_garage_managing_app.ui.my.account;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import dk.via.and1.and1_garage_managing_app.data.user.User;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;
import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

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

    public void updateUser(User user)
    {
        userRepository.updateUser(user, new MyCallback() {
            @Override
            public void OnError(String message)
            {
                System.out.println("Error: " + message);
            }

            @Override
            public void onSuccess()
            {
                System.out.println("User updated successfully");
            }
        });
    }
}