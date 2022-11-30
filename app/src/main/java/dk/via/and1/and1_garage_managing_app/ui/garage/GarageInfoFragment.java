package dk.via.and1.and1_garage_managing_app.ui.garage;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.Date;

import dk.via.and1.and1_garage_managing_app.data.garage.Garage;
import dk.via.and1.and1_garage_managing_app.databinding.FragmentGarageInfoBinding;

public class GarageInfoFragment extends Fragment {

    private FragmentGarageInfoBinding binding;
    private GarageInfoViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentGarageInfoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(GarageInfoViewModel.class);

        viewModel.getGarageLiveData().observe(getViewLifecycleOwner(), garage -> {
            if (garage != null)
            {
                binding.cityNameEditText.setText(garage.getCity());
                binding.postCodeEditText.setText(garage.getPostCode());
                binding.streetEditText.setText(garage.getStreet());
                binding.streetNumberEditText.setText(garage.getStreetNumber());
                binding.lightTimerEditText.setText(String.valueOf(garage.getLightTimer()));
                binding.gateTimerEditText.setText(String.valueOf(garage.getGateTimer()));
            }
        });

        binding.updateButton.setOnClickListener(view -> {
            String city = binding.cityNameEditText.getText().toString();
            String postCode = binding.postCodeEditText.getText().toString();
            String street = binding.streetEditText.getText().toString();
            String streetNumber = binding.streetNumberEditText.getText().toString();
            int lightTimer = Integer.parseInt(binding.lightTimerEditText.getText().toString());
            int gateTimer = Integer.parseInt(binding.gateTimerEditText.getText().toString());

            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

            Garage garage = new Garage(new Date(), new Date(), city, postCode, street, streetNumber, lightTimer, gateTimer);
            viewModel.updateGarageInfo(garage);
        });

        viewModel.getResult().observe(getViewLifecycleOwner(), result -> {
            Toast.makeText(binding.getRoot().getContext(), result, Toast.LENGTH_LONG).show();
        });
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

}