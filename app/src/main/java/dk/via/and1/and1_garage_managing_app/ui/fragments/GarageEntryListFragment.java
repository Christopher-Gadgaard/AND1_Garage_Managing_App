package dk.via.and1.and1_garage_managing_app.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dk.via.and1.and1_garage_managing_app.R;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageAction;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageEntry;
import dk.via.and1.and1_garage_managing_app.data.user.User;
import dk.via.and1.and1_garage_managing_app.databinding.FragmentGarageEntryListBinding;
import dk.via.and1.and1_garage_managing_app.ui.GarageEntryAdapter;

public class GarageEntryListFragment extends Fragment
{

    private FragmentGarageEntryListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentGarageEntryListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.garageEntryListRecyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        List<GarageEntry> garageEntries = new ArrayList<>();

        User user = new User(); //TODO REPLACE THIS
        Date date = new Date(2022,11,14,0,56);
        GarageEntry garageEntry = new GarageEntry(user, date, GarageAction.OPEN_GATE);

        for (int i = 0; i < 30; i++)
        {
            garageEntries.add(garageEntry);
        }

        GarageEntryAdapter adapter = new GarageEntryAdapter(garageEntries);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

}