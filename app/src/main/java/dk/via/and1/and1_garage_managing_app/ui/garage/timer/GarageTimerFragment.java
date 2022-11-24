package dk.via.and1.and1_garage_managing_app.ui.garage.timer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

import dk.via.and1.and1_garage_managing_app.data.garage.GarageAction;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageActions;
import dk.via.and1.and1_garage_managing_app.databinding.FragmentGarageTimerBinding;
import dk.via.and1.and1_garage_managing_app.ui.garage.timer.GarageTimerViewModel;

public class GarageTimerFragment extends Fragment {
    private FragmentGarageTimerBinding binding;
    private GarageTimerViewModel viewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentGarageTimerBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(GarageTimerViewModel.class);
        viewModel.init();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        binding.setGpsToGarageButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
        });

        binding.openGarageButton.setOnClickListener(v -> {
            Date date = new Date();
            String userId = viewModel.getCurrentFirebaseUser().getValue().getUid();
            viewModel.garageAction(new GarageAction(userId, date, GarageActions.OPEN_GATE));
        });

        binding.closeGarageButton.setOnClickListener(v->{
            Date date = new Date();
            String userId = viewModel.getCurrentFirebaseUser().getValue().getUid();
            viewModel.garageAction(new GarageAction(userId, date, GarageActions.CLOSE_GATE));
        });

        binding.garageLightsButton.setOnClickListener(v->{
            Date date = new Date();
            String userId = viewModel.getCurrentFirebaseUser().getValue().getUid();
            viewModel.garageAction(new GarageAction(userId, date, GarageActions.LIGHTS_ON));
        });
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

}