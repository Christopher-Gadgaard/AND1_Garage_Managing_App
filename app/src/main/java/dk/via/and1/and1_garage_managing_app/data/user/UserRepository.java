package dk.via.and1.and1_garage_managing_app.data.user;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {
    private final UserAuthLiveData currentUser;
    private static UserRepository instance;
    private DatabaseReference myRef;
    private UserLiveData userLiveData;
    private FirebaseAuth fAuth;

    private UserRepository()
    {
        currentUser = new UserAuthLiveData();
    }

    public static synchronized UserRepository getInstance()
    {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void init()
    {
        fAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance("https://and1-garage-managing-app-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users").child(getCurrentUser().getValue().getUid());
        userLiveData = new UserLiveData(myRef);
    }

    public LiveData<FirebaseUser> getCurrentUser()
    {
        return currentUser;
    }

    public void signOut()
    {
        fAuth.signOut();
    }

    public FirebaseAuth getfAuth()
    {
        return fAuth;
    }

    public void registerUser(User user, String password, myCallback callback)
    {
      fAuth.createUserWithEmailAndPassword(user.getEmail(), password).addOnCompleteListener(task -> {
          myRef.push().setValue(user).addOnCompleteListener(task1 -> {
              callback.onSuccess();
          });
      }); //TODO look here with kasper
    }



    public UserLiveData getUser()
    {
        return userLiveData;
    }

    public interface myCallback
    {
        void OnError();
        void onSuccess();
    }




}
