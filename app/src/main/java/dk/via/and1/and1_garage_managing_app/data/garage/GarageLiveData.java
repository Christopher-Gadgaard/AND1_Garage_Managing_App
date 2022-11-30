package dk.via.and1.and1_garage_managing_app.data.garage;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class GarageLiveData extends LiveData<Garage> {
    DatabaseReference databaseReference;

    public GarageLiveData( DatabaseReference databaseReference)
    {
        this.databaseReference = databaseReference;
    }

    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot)
        {
            Garage garage = snapshot.getValue(Garage.class);
            setValue(garage);
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
