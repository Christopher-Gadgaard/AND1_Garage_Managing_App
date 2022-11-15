package dk.via.and1.and1_garage_managing_app.data.garage;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GarageEntryRepository
{
    private static GarageEntryRepository instance;
    private DatabaseReference myRef;
    private GarageEntryLiveData garageEntry;

    private GarageEntryRepository(){}

    public static synchronized GarageEntryRepository getInstance(){
        if (instance == null)
            instance = new GarageEntryRepository();
        return instance;
    }

    public void init(String userId)
    {
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

    }

}
