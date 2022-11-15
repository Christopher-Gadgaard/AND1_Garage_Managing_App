package dk.via.and1.and1_garage_managing_app.data.user;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRepository
{
    private final UserLiveData currentUser;
    private final Application app;
    private static UserRepository instance;
    private final FirebaseAuth fAuth;

    private UserRepository(Application app) {
        this.app = app;
        currentUser = new UserLiveData();
        fAuth = FirebaseAuth.getInstance();
    }

    public static synchronized UserRepository getInstance(Application app) {
        if(instance == null)
            instance = new UserRepository(app);
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public FirebaseAuth getAuth()
    {
        return fAuth;
    }

    public void signOut(){
        FirebaseAuth.getInstance().signOut();
    }

}
