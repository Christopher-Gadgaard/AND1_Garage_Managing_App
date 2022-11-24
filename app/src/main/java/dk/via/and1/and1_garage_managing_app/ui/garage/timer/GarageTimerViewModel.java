package dk.via.and1.and1_garage_managing_app.ui.garage.timer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import dk.via.and1.and1_garage_managing_app.data.garage.GarageAction;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageActionRepository;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;
import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

public class GarageTimerViewModel extends AndroidViewModel {
    UserRepository userRepository;
    GarageActionRepository garageActionRepository;

    MutableLiveData<String> result;

    public GarageTimerViewModel(@NonNull Application application)
    {
        super(application);
        userRepository = UserRepository.getInstance();
        garageActionRepository = GarageActionRepository.getInstance();
    }

    public void init()
    {

    }

    public void garageAction(GarageAction garageAction)
    {
        String userId = userRepository.getCurrentFirebaseUser().getValue().getUid();
        garageActionRepository.init(userId);
        garageActionRepository.garageAction(garageAction, new MyCallback() {
            @Override
            public void OnError(String message)
            {
                result.setValue("Could not connect to garage");
            }

            @Override
            public void onSuccess()
            {

            }
        });
    }

    public LiveData<FirebaseUser> getCurrentFirebaseUser()
    {
        return userRepository.getCurrentFirebaseUser();
    }
}
