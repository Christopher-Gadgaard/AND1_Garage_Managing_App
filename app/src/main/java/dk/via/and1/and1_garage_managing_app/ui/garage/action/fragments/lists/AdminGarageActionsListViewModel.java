package dk.via.and1.and1_garage_managing_app.ui.garage.action.fragments.lists;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dk.via.and1.and1_garage_managing_app.data.garage.GarageAction;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageActionRepository;
import dk.via.and1.and1_garage_managing_app.data.user.User;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;

public class AdminGarageActionsListViewModel extends AndroidViewModel
{
    private GarageActionRepository garageActionRepository;
    private UserRepository userRepository;

    public AdminGarageActionsListViewModel(@NonNull Application application)
    {
        super(application);
        garageActionRepository = GarageActionRepository.getInstance();
        userRepository = UserRepository.getInstance();
    }

    public void init()
    {
        String userId = userRepository.getCurrentFirebaseUser().getValue().getUid();
        garageActionRepository.init(userId);
    }

    public LiveData<List<GarageAction>> getAdminGarageActions()
    {
        return garageActionRepository.getAdminGarageActions();
    }

}