package dk.via.and1.and1_garage_managing_app.data.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

public class UserRepository {
    private final UserAuthLiveData currentUser;
    private static UserRepository instance;
    private DatabaseReference myRef;
    private UserLiveData currentUserLiveData;
    private FirebaseAuth fAuth;
    private MutableLiveData<User> getUserMutableLiveData;

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

    public void init(String userId)
    {
        fAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance("https://and1-garage-managing-app-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");
        currentUserLiveData = new UserLiveData(myRef.child(userId));
        getUserMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<FirebaseUser> getCurrentFirebaseUser()
    {
        return currentUser;
    }

    public void getUserById(String id, MyCallback callback)
    {
        myRef.child(id).get().addOnSuccessListener(task -> {
                callback.onSuccess();

        });
    }


    public UserLiveData getUser()
    {
        return currentUserLiveData;
    }

    public void login(String email, String password, MyCallback callback)
    {
        fAuth = FirebaseAuth.getInstance();
        fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            callback.onSuccess();
        }).addOnFailureListener(e -> {
            callback.OnError(e.getMessage());
        });
    }

    public void logout()
    {
        fAuth.signOut();
    }

    public void recoverPassword(String email, MyCallback callback)
    {
        fAuth = FirebaseAuth.getInstance();
        fAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener(e -> {
                    callback.onSuccess();
                })
                .addOnFailureListener(e -> callback.OnError(e.getMessage()));
    }

    public void registerUser(User user, String password, MyCallback callback)
    {
        fAuth = FirebaseAuth.getInstance();
        fAuth.createUserWithEmailAndPassword(user.getEmail(), password).addOnSuccessListener(task -> {
            String userId = fAuth.getUid();
            myRef = FirebaseDatabase.getInstance("https://and1-garage-managing-app-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users").child(userId);
            myRef.setValue(user).addOnSuccessListener(e -> {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(user.getFirstName() + " " + user.getLastName())
                        .build();
                fAuth.getCurrentUser().updateProfile(profileUpdates).addOnSuccessListener(e1 -> {
                    callback.onSuccess();
                }).addOnFailureListener(e1 -> {
                    callback.OnError(e1.getMessage());
                });
            }).addOnFailureListener(e -> {
                callback.OnError(e.getMessage());
            });
        }).addOnFailureListener(e -> {
            callback.OnError(e.getMessage());
        });
    }

    public void updateUser(User user, MyCallback callback) //TODO IMPLEMENT THIS
    {
        myRef = FirebaseDatabase.getInstance("https://and1-garage-managing-app-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users").child("");
        myRef.setValue(user).addOnSuccessListener(e -> {
            callback.onSuccess();
        }).addOnFailureListener(e -> {
            callback.OnError(e.getMessage());
        });
    }
}
