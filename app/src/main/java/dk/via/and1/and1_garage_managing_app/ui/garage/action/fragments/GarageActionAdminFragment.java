package dk.via.and1.and1_garage_managing_app.ui.garage.action.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.via.and1.and1_garage_managing_app.databinding.FragmentGarageActionAdminBinding;


public class GarageActionAdminFragment extends Fragment
{

    private FragmentGarageActionAdminBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentGarageActionAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        binding = null;
    }
}