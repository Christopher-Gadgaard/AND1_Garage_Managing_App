package dk.via.and1.and1_garage_managing_app.data.user;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;

public class UserByIdLiveData extends LiveData<User> {
    DatabaseReference databaseReference;

    public UserByIdLiveData(DatabaseReference myRef)
    {
        databaseReference = myRef;
    }

}
