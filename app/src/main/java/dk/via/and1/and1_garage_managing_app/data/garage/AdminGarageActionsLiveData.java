package dk.via.and1.and1_garage_managing_app.data.garage;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminGarageActionsLiveData extends LiveData<List<GarageAction>> {

    DatabaseReference databaseReference;

    public AdminGarageActionsLiveData(DatabaseReference databaseReference)
    {
        this.databaseReference = databaseReference;
    }

    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot)
        {
            List<GarageAction> garageActions = new ArrayList<>();
            snapshot.getChildren().forEach(dataSnapshot -> {
                dataSnapshot.getChildren().forEach(dataSnapshot1 -> {
                    garageActions.add(dataSnapshot1.getValue(GarageAction.class));
                });
            });
            setValue(garageActions);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error)
        {

        }
    };

    @Override
    protected void onActive()
    {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive()
    {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}
