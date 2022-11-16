package dk.via.and1.and1_garage_managing_app.data.user;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository
{
    private final UserAuthLiveData currentUser;
    private static UserRepository instance;
    private DatabaseReference myRef;
    private UserInfoLiveData userInfoLiveData;

    private UserRepository() {
        currentUser = new UserAuthLiveData();
    }

    public static synchronized UserRepository getInstance() {
        if(instance == null)
        {
            instance = new UserRepository();
        }
        return instance;
    }

    public void init()
    {
        String userId = getCurrentUser().getValue().getUid();
        myRef = FirebaseDatabase.getInstance("https://and1-garage-managing-app-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("users");
        userInfoLiveData = new UserInfoLiveData(myRef);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void signOut(){
        FirebaseAuth.getInstance().signOut();
    }

    public void signUp(UserInfo userInfo, String password){ //TODO MAYBE REMOVE THIS ??
        String userId = instance.getCurrentUser().getValue().getUid();
       myRef.child("users").child(userId).setValue(userInfo);
    }

    public UserInfoLiveData getUserInfo()
    {
        return userInfoLiveData;
    }

}
