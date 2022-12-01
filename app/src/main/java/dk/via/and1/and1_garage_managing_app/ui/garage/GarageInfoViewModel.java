package dk.via.and1.and1_garage_managing_app.ui.garage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import dk.via.and1.and1_garage_managing_app.data.garage.Garage;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageRepository;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;
import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

public class GarageInfoViewModel extends AndroidViewModel {

    UserRepository userRepository;
    GarageRepository garageRepository;

    MutableLiveData<String> result;

    public GarageInfoViewModel(@NonNull Application application)
    {
        super(application);
        userRepository = UserRepository.getInstance();
        userRepository.initDatabase();
        garageRepository = GarageRepository.getInstance();
        result = new MutableLiveData<>();
    }

    public void updateGarageInfo(Garage garage)
    {
        garageRepository.updateGarage(garage, new MyCallback() {
            @Override
            public void OnError(String message)
            {
                result.setValue(message);
            }

            @Override
            public void onSuccess()
            {
                result.setValue("Update was successful");
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