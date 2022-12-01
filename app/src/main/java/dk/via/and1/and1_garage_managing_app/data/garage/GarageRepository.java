package dk.via.and1.and1_garage_managing_app.data.garage;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.List;

import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

public class GarageRepository {
    private static GarageRepository instance;

    private DatabaseReference myGarageActionRef;
    private DatabaseReference myGarageRef;
    private FirebaseDatabase database;

    private AdminGarageActionsLiveData adminGarageActionsLiveData;
    private UserGarageActionsLiveData userGarageActionsLiveData;
    private GarageLiveData garageLiveData;

    private String userId;

    public static synchronized GarageRepository getInstance()
    {
        if (instance == null)
            instance = new GarageRepository();
        return instance;
    }


    public void initDatabase(String userId)
    {
        this.userId = userId;

        database = FirebaseDatabase.getInstance();

        myGarageRef = database.getReference("garage");
        myGarageActionRef = database.getReference().child("garageActions");

        userGarageActionsLiveData = new UserGarageActionsLiveData(myGarageActionRef.child(userId));
        adminGarageActionsLiveData = new AdminGarageActionsLiveData(myGarageActionRef);
        garageLiveData = new GarageLiveData(myGarageRef);
    }

    public void garageAction(GarageAction garageAction, MyCallback callback)
    {

        myGarageActionRef.child(userId).push().setValue(garageAction).addOnSuccessListener(e -> {
            callback.onSuccess();
        }).addOnFailureListener(e -> {
            callback.OnError(e.getMessage());
        });
    }

    public LiveData<List<GarageAction>> getUserGarageActions()
    {
        return userGarageActionsLiveData;
    }

    public LiveData<List<GarageAction>> getAdminGarageActions()
    {
        return adminGarageActionsLiveData;
    }

    public void updateGarage(Garage garage, MyCallback callback)
    {
        myGarageRef.setValue(garage).addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                callback.onSuccess();
            }
            else
            {
                callback.OnError(task.getException().getMessage());
            }
        });
    }

    public void setGarageGateCloseTime(Date date,MyCallback callback)
    {
      myGarageRef.child("gateCloseTime").setValue(date).addOnCompleteListener(task -> {
          if (task.isSuccessful())
          {
              callback.onSuccess();
          }
          else
          {
                callback.OnError(task.getException().getMessage());
          }
      });
    }

    public void setGarageLightOffTime(Date date,MyCallback callback)
    {
        myGarageRef.child("lightOffTime").setValue(date).addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                callback.onSuccess();
            }
            else
            {
                callback.OnError(task.getException().getMessage());
            }
        });
    }

    public LiveData<Garage> getGarageLiveData()
    {
        return garageLiveData;
    }

}
