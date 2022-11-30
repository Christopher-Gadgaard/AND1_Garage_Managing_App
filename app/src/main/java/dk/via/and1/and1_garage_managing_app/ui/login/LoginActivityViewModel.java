package dk.via.and1.and1_garage_managing_app.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;
import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

public class LoginActivityViewModel extends AndroidViewModel {
    UserRepository userRepository;
    MutableLiveData<String> result;

    public LoginActivityViewModel(@NonNull Application app)
    {
        super(app);
        userRepository = UserRepository.getInstance();
        result = new MutableLiveData<>();
    }

    public LiveData<String> getResult()
    {
        return result;
    }

    public void recoverPassword(String email)
    {
        userRepository.recoverPassword(email, new MyCallback() {
            @Override
            public void OnError(String message)
            {
                result.setValue(message);
            }

            @Override
            public void onSuccess()
            {
                result.setValue("Reset link was sent to your email");
            }
        });
    }

    public void login(String email, String password)
    {
        userRepository.login(email, password, new MyCallback() {
            @Override
            public void OnError(String message)
            {
                result.setValue(message);
            }

            @Override
            public void onSuccess()
            {
                result.setValue("Login was successful");
            }
        });
    }

    public LiveData<FirebaseUser> getCurrentFirebaseUser()
    {
        return userRepository.getUserAuthLiveData();
    }
}
