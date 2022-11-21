package dk.via.and1.and1_garage_managing_app.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;

import dk.via.and1.and1_garage_managing_app.databinding.FragmentGarageTimerBinding;

public class GarageTimerFragment extends Fragment
{
    private FragmentGarageTimerBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentGarageTimerBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        binding.setGpsToGarageButton.setOnClickListener(view1 -> {
            FirebaseAuth.getInstance().signOut();
        });
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

}