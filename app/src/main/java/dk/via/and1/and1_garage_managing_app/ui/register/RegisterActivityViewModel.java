package dk.via.and1.and1_garage_managing_app.ui.register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import dk.via.and1.and1_garage_managing_app.data.user.User;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;
import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

public class RegisterActivityViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    MutableLiveData<String> registerResult;

    public RegisterActivityViewModel(@NonNull Application app)
    {
        super(app);
        userRepository = UserRepository.getInstance();
        registerResult = new MutableLiveData<>();
    }

    public LiveData<String> getRegisterResult()
    {
        return registerResult;
    }

    public LiveData<FirebaseUser> getCurrentFirebaseUser()
    {
        return userRepository.getUserAuthLiveData();
    }

    public void registerUser(User user, String password)
    {
        userRepository.registerUser(user, password, new MyCallback() {
            @Override
            public void OnError(String message)
            {
                registerResult.setValue(message);
            }

            @Override
            public void onSuccess()
            {
                registerResult.setValue("Registering was successful");
            }
        });
    }
}
