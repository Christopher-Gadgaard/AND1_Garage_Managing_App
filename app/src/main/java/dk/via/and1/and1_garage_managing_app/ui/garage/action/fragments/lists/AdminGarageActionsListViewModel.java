package dk.via.and1.and1_garage_managing_app.ui.garage.action.fragments.lists;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dk.via.and1.and1_garage_managing_app.data.garage.GarageAction;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageRepository;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;

public class AdminGarageActionsListViewModel extends AndroidViewModel
{
    private GarageRepository garageRepository;
    private UserRepository userRepository;

    public AdminGarageActionsListViewModel(@NonNull Application application)
    {
        super(application);
        garageRepository = GarageRepository.getInstance();
        userRepository = UserRepository.getInstance();
    }

    public void init()
    {
        String userId = userRepository.getUserAuthLiveData().getValue().getUid();
        garageRepository.init(userId);
    }

    public LiveData<List<GarageAction>> getAdminGarageActions()
    {
        return garageRepository.getAdminGarageActions();
    }

}