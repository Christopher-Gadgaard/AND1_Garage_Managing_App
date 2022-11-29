package dk.via.and1.and1_garage_managing_app.data.garage;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.PrimitiveIterator;

import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

public class GarageActionRepository {
    private static GarageActionRepository instance;
    private DatabaseReference myGarageActionRef;
    private DatabaseReference myUserRef;
    private FirebaseDatabase database;
    private AdminGarageActionsLiveData adminGarageActionsLiveData;
    private UserGarageActionsLiveData userGarageActionsLiveData;
    private String userId;

    public static synchronized GarageActionRepository getInstance()
    {
        if (instance == null)
            instance = new GarageActionRepository();
        return instance;
    }

    public void init(String userId)
    {
        this.userId = userId;
        database = FirebaseDatabase.getInstance();
        myUserRef = database.getReference().child("Users").child(userId);
        myGarageActionRef = database.getReference().child("garageActions");
        userGarageActionsLiveData = new UserGarageActionsLiveData(myGarageActionRef.child(userId));
        adminGarageActionsLiveData = new AdminGarageActionsLiveData(myGarageActionRef);
    }

    public void garageAction(GarageAction garageAction, MyCallback callback)
    {
        String actionId = myGarageActionRef.push().getKey();
        myGarageActionRef.child(userId).child(actionId).setValue(garageAction).addOnSuccessListener(e -> {
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

}
