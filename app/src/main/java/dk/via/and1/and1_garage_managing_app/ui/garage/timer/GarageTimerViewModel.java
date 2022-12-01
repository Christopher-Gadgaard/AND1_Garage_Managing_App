package dk.via.and1.and1_garage_managing_app.ui.garage.timer;

import android.app.Application;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;
import java.util.Locale;

import dk.via.and1.and1_garage_managing_app.data.garage.Garage;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageAction;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageRepository;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;
import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

public class GarageTimerViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private final GarageRepository garageRepository;

    MutableLiveData<String> result;

    public GarageTimerViewModel(@NonNull Application application)
    {
        super(application);
        userRepository = UserRepository.getInstance();
        userRepository.initDatabase();

        garageRepository = GarageRepository.getInstance();
        garageRepository.initDatabase(FirebaseAuth.getInstance().getUid());
        result = new MutableLiveData<>();
    }


    public void garageAction(GarageAction garageAction)
    {
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
