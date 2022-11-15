package dk.via.and1.and1_garage_managing_app.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import dk.via.and1.and1_garage_managing_app.data.garage.GarageEntry;

public class GarageEntryListViewModel extends ViewModel
{
    private final MutableLiveData <List<GarageEntry>> garageEntries;

    public GarageEntryListViewModel()
    {
        garageEntries = new MutableLiveData<>();
        List<GarageEntry> newList = new ArrayList<>();
        garageEntries.setValue(newList);
    }

    public LiveData<List<GarageEntry>> getGarageEntries()
    {
        return garageEntries;
    }

}
