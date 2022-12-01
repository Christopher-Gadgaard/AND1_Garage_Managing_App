package dk.via.and1.and1_garage_managing_app.ui.my.account;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import dk.via.and1.and1_garage_managing_app.data.user.User;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;
import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

public class MyAccountViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    MutableLiveData<String> result;

    public MyAccountViewModel(@NonNull Application app)
    {
        super(app);
        userRepository = UserRepository.getInstance();
        userRepository.initDatabase();
        result = new MutableLiveData<>();
    }

    public LiveData<User> getUser()
    {
        return userRepository.getUserLiveData();
    }

    public void updateUser(User user)
    {
        userRepository.updateUser(user, new MyCallback() {
            @Override
            public void OnError(String message)
            {
                result.setValue(message);
            }

            @Override
            public void onSuccess()
            {
                result.setValue("Account was updated successfully");
            }
        });
    }

    public void changePassword(String password)
    {
        userRepository.changePassword(password, new MyCallback() {
            @Override
            public void OnError(String message)
            {
                result.setValue(message);
            }

            @Override
            public void onSuccess()
            {
                result.setValue("Password changed successfully");
            }
        });
    }

    public LiveData<String> getResult()
    {
        return result;
    }
}