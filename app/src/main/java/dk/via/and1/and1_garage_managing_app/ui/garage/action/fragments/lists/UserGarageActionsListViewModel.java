package dk.via.and1.and1_garage_managing_app.ui.garage.action.fragments.lists;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import dk.via.and1.and1_garage_managing_app.data.garage.GarageAction;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageRepository;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;

public class UserGarageActionsListViewModel extends AndroidViewModel
{
    private GarageRepository garageRepository;
    private UserRepository userRepository;

    public UserGarageActionsListViewModel(@NonNull Application application)
    {
        super(application);
        userRepository = UserRepository.getInstance();
        userRepository.initAuth();
        userRepository.initDatabase();
        garageRepository = GarageRepository.getInstance();
        garageRepository.initDatabase(FirebaseAuth.getInstance().getUid());
    }




    public LiveData<List<GarageAction>> getUserGarageActions()
    {
        return garageRepository.getUserGarageActions();
    }

}
