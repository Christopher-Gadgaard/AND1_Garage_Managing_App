package dk.via.and1.and1_garage_managing_app.data.user;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dk.via.and1.and1_garage_managing_app.utils.MyCallback;

public class UserRepository {
    private final UserAuthLiveData currentUser;
    private static UserRepository instance;
    private DatabaseReference myRef;
    private UserLiveData currentUserLiveData;
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

    public void init(String userId)
    {
        fAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance("https://and1-garage-managing-app-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");
        currentUserLiveData = new UserLiveData(myRef.child(userId));
    }

    public LiveData<FirebaseUser> getCurrentFirebaseUser()
    {
        return currentUser;
    }


    public UserLiveData getUser()
    {
        return currentUserLiveData;
    }

    public void login(String email, String password, MyCallback callback)
    {
        System.out.println(email + " " + password);
        fAuth = FirebaseAuth.getInstance();
        fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            callback.onSuccess();
        }).addOnFailureListener(e -> {
            callback.OnError(e.getMessage());
        });
    }

    public void logout()
    {
        fAuth = FirebaseAuth.getInstance();
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

    public void updateUser(User user, MyCallback callback)
    {
        myRef = FirebaseDatabase.getInstance("https://and1-garage-managing-app-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users").child(currentUser.getValue().getUid());
        myRef.setValue(user).addOnCompleteListener(e -> {
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
        public void changePassword (String password, MyCallback callback)
        {
            currentUser.getValue().updatePassword(password).addOnSuccessListener(e -> {
                callback.onSuccess();
            }).addOnFailureListener(e -> {
                callback.OnError(e.getMessage());
            });
        }
    }
