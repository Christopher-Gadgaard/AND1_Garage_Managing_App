package dk.via.and1.and1_garage_managing_app.data.user;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class UserInfoLiveData extends LiveData<UserInfo>
{
    DatabaseReference databaseReference;

    private final ValueEventListener listener = new ValueEventListener()
    {

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot)
        {
            //TODO ASK ABOUT THIS
            for (DataSnapshot ds : snapshot.getChildren())
            {
                UserInfo userInfo = ds.getValue(UserInfo.class);
                setValue(userInfo);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error)
        {
          //TODO
        }
    };


    public UserInfoLiveData(DatabaseReference myRef)
    {
        databaseReference = myRef;
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
