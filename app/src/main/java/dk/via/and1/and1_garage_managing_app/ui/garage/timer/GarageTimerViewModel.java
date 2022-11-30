package dk.via.and1.and1_garage_managing_app.ui.garage.timer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

import dk.via.and1.and1_garage_managing_app.data.garage.Garage;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageAction;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageRepository;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;
import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

public class GarageTimerViewModel extends AndroidViewModel {
    UserRepository userRepository;
    GarageRepository garageRepository;

    MutableLiveData<String> result;

    public GarageTimerViewModel(@NonNull Application application)
    {
        super(application);
        userRepository = UserRepository.getInstance();
        garageRepository = GarageRepository.getInstance();
        result = new MutableLiveData<>();
    }

    public void init()
    {

    }

    public void garageAction(GarageAction garageAction)
    {
        String userId = userRepository.getUserAuthLiveData().getValue().getUid();
        garageRepository.init(userId);
        garageRepository.garageAction(garageAction, new MyCallback() {
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
        return userRepository.getUserAuthLiveData();
    }

    public void setGarageGateCloseTime(Date date)
    {
        garageRepository.setGarageGateCloseTime(date, new MyCallback() {
            @Override
            public void OnError(String message)
            {
                result.setValue("Could not connect to garage");
            }

            @Override
            public void onSuccess()
            {
                result.setValue("Garage gate close time set");//TODO CHANGE THIS
            }
        });
    }

    public void setGarageLightOffTime(Date date)
    {
        garageRepository.setGarageLightOffTime(date, new MyCallback() {
            @Override
            public void OnError(String message)
            {
                result.setValue("Could not connect to garage");
            }

            @Override
            public void onSuccess()
            {
                result.setValue("Garage light off time set");//TODO CHANGE THIS
            }
        });
    }

    public LiveData<String> getResult()
    {
        return result;
    }

    public LiveData<Garage> getGarageLiveData()
    {
        return garageRepository.getGarageLiveData();
    }
}
