package dk.via.and1.and1_garage_managing_app.data.garage;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class GarageEntryLiveData extends LiveData<List<GarageEntry>>
{
    private final ValueEventListener listener = new ValueEventListener()
    {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot)
        {
         //   List<GarageEntry> garageEntry = snapshot.getValue(); //TODO FIGURE OUT HOW TO GET LIST AS LIVEDATA
          //  setValue(garageEntry);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error)
        {

        }
    };

    DatabaseReference databaseReference;

    public GarageEntryLiveData(DatabaseReference ref)
    {
        databaseReference = ref;
    }

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
