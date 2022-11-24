package dk.via.and1.and1_garage_managing_app.ui.garage.action.fragments.lists;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dk.via.and1.and1_garage_managing_app.data.garage.GarageAction;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageActionRepository;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;

public class UserGarageActionsListViewModel extends AndroidViewModel
{
    private GarageActionRepository garageActionRepository;
    private UserRepository userRepository;

    public UserGarageActionsListViewModel(@NonNull Application application)
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

    public LiveData<List<GarageAction>> getUserGarageActions()
    {
        return garageActionRepository.getUserGarageActions();
    }

}
