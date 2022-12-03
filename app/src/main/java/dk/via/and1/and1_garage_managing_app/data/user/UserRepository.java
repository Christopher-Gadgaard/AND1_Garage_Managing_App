package dk.via.and1.and1_garage_managing_app.data.user;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

public class UserRepository {
    private static UserRepository instance;

    private UserAuthLiveData userAuthLiveData;
    private UserLiveData userLiveData;

    private DatabaseReference myUserRef;

    private FirebaseAuth fAuth;
    private FirebaseDatabase database;


    public static synchronized UserRepository getInstance()
    {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void initDatabase()
    {
        database = FirebaseDatabase.getInstance();
        myUserRef = database.getReference("users");
        userLiveData = new UserLiveData(myUserRef.child(FirebaseAuth.getInstance().getUid())); //TODO ASK ABOUT THIS
    }

    public void initAuth()
    {
        fAuth = FirebaseAuth.getInstance();
        userAuthLiveData = new UserAuthLiveData();
    }

    public LiveData<FirebaseUser> getUserAuthLiveData()
    {
        return userAuthLiveData;
    }

    public LiveData<User> getUserLiveData()
    {
        return userLiveData;
    }

    public void login(String email, String password, MyCallback callback)
    {
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
        fAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener(e -> {
                    callback.onSuccess();
                })
                .addOnFailureListener(e -> callback.OnError(e.getMessage()));
    }

    public void registerUser(User user, String password, MyCallback callback)
    {
        fAuth.createUserWithEmailAndPassword(user.getEmail(), password).addOnSuccessListener(task -> {
            initDatabase();
            String userId = fAuth.getUid();
            myUserRef.child(userId).setValue(user).addOnSuccessListener(e -> {
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

    public void updateUser(User user, MyCallback callback)
    {
        String userId = fAuth.getUid();
        myUserRef.child(userId).setValue(user).addOnCompleteListener(e -> {
            if (e.isSuccessful()) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(user.getFirstName() + " " + user.getLastName())
                        .build();
                fAuth.getCurrentUser().updateProfile(profileUpdates).addOnCompleteListener(e1 -> {
                    if (e1.isSuccessful()) {
                        fAuth.getCurrentUser().updateEmail(user.getEmail()).addOnCompleteListener(e2 -> {
                            if (e2.isSuccessful()) {
                                callback.onSuccess();
                            } else {
                                callback.OnError(e2.getException().getMessage());
                            }
                        });
                    } else {
                        callback.OnError(e1.getException().getMessage());
                    }
                });
            } else {
                callback.OnError(e.getException().getMessage());
            }
        });
    }

    public void changePassword(String password, MyCallback callback)
    {
        fAuth.getCurrentUser().updatePassword(password).addOnSuccessListener(e -> {
            callback.onSuccess();
        }).addOnFailureListener(e -> {
            callback.OnError(e.getMessage());
        });
    }
}



