package dk.via.and1.and1_garage_managing_app.data.user;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class UserLiveData extends LiveData<User>
{
    DatabaseReference databaseReference;

    public UserLiveData(DatabaseReference myRef)
    {
        databaseReference = myRef;
    }

    private final ValueEventListener listener = new ValueEventListener()
    {

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot)
        {
        User user = snapshot.getValue(User.class);
        setValue(user);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error)
        {
          //TODO
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
