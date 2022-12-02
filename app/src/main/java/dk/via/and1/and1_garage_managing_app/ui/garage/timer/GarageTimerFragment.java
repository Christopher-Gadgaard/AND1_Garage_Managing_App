package dk.via.and1.and1_garage_managing_app.ui.garage.timer;

import static dk.via.and1.and1_garage_managing_app.R.drawable.bulb_off;
import static dk.via.and1.and1_garage_managing_app.R.drawable.bulb_on;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;
import java.util.Locale;

import dk.via.and1.and1_garage_managing_app.R;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageAction;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageActions;
import dk.via.and1.and1_garage_managing_app.databinding.DialogChangePasswordBinding;
import dk.via.and1.and1_garage_managing_app.databinding.DialogHelpBinding;
import dk.via.and1.and1_garage_managing_app.databinding.FragmentGarageTimerBinding;

public class GarageTimerFragment extends Fragment {
    private FragmentGarageTimerBinding binding;
    private DialogHelpBinding dialogBinding;
    private GarageTimerViewModel viewModel;

    private CountDownTimer gateCountDownTimer, lightCountDownTimer;
    private long gateTimeLeftInMilliseconds, lightTimeLeftInMilliseconds;
    private int gateCloseTimer, lightCloseTimer;
    private Boolean isLightOn;
    private String city, postCode, street, streetNo;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentGarageTimerBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(GarageTimerViewModel.class);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

       observeGarage();

        binding.setGpsToGarageButton.setOnClickListener(v -> {
           onSetGpsClick();
        });

        binding.openGarageButton.setOnClickListener(v -> {
          onOpenGarageClick();
        });

        binding.closeGarageButton.setOnClickListener(v -> {
            onCloseGarageClick();
        });

        binding.garageLightsButton.setOnClickListener(v -> {
           onGarageLightsClick();
        });

        binding.helpButton.setOnClickListener(v -> {
            onHelpClick();
        });
    }

    private void onHelpClick()
    {
        dialogBinding = DialogHelpBinding.inflate(getLayoutInflater());
        final AlertDialog.Builder dialog = new AlertDialog.Builder(binding.getRoot().getContext(), R.style.AlertDialog);
        dialog.setView(dialogBinding.getRoot());
        dialog.setTitle("Need help?");
        dialog.setMessage("Send us an email and we will get back to you as soon as possible.");
        dialog.setPositiveButton("Send", (dialog1, which) -> {
            String subject = dialogBinding.editTextSubject.getText().toString();
            String message = dialogBinding.editTextTextMultiLine.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, "309283@via.dk");
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(intent);

        });
        dialog.setNegativeButton("Cancel", (dialog12, which) -> dialog12.dismiss());
        dialog.create().show();
    }

    public void observeGarage()
    {
        viewModel.getGarageLiveData().observe(getViewLifecycleOwner(), garage -> {
            if (garage != null) {

                lightCloseTimer = garage.getLightTimer();
                gateCloseTimer = garage.getGateTimer();
                city = garage.getCity();
                postCode = garage.getPostCode();
                street = garage.getStreet();
                streetNo = garage.getStreetNo();


                if (garage.getGateCloseTime().after(new Date())) {
                    gateTimeLeftInMilliseconds = garage.getGateCloseTime().getTime() - new Date().getTime();
                    binding.gateCountDownTimerTextView.setVisibility(View.VISIBLE);
                    binding.gateCountDownTimerImageView.setVisibility(View.INVISIBLE);
                    startGateTimer();
                } else {
                    resetGateTimer();
                    binding.gateCountDownTimerProgressBar.setProgress(100);
                    binding.gateCountDownTimerTextView.setVisibility(View.INVISIBLE);
                    binding.gateCountDownTimerImageView.setVisibility(View.VISIBLE);
                }


                if (garage.getLightOffTime().after(new Date())) {
                    lightTimeLeftInMilliseconds = garage.getLightOffTime().getTime() - new Date().getTime();
                    binding.lightCountDownTimerTextView.setVisibility(View.VISIBLE);
                    binding.lightCountDownTimerProgressBar.setVisibility(View.VISIBLE);
                    binding.garageLightsButton.setImageResource(bulb_on);
                    isLightOn = true;
                    startLightTimer();
                } else {
                    resetLightTimer();
                    binding.lightCountDownTimerProgressBar.setVisibility(View.INVISIBLE);
                    binding.lightCountDownTimerTextView.setVisibility(View.INVISIBLE);
                    binding.garageLightsButton.setImageResource(bulb_off);
                    isLightOn = false;
                }
            }
        });
    }

    public void onSetGpsClick()
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/dir/?api=1&destination="+city+"+"+postCode+"+"+street+"+"+streetNo+"&travelmode=driving"));
        startActivity(intent);
    }

    public void onOpenGarageClick()
    {
        Date date = new Date();
        String id = FirebaseAuth.getInstance().getUid();
        viewModel.garageAction(new GarageAction(id, date, GarageActions.OPEN_GATE));
        long time = date.getTime();
        viewModel.setGarageGateCloseTime(new Date(time + ((long) gateCloseTimer * 60 * 1000)));
    }

    public void onCloseGarageClick()
    {
        Date date = new Date();
        String id = FirebaseAuth.getInstance().getUid();
        viewModel.garageAction(new GarageAction(id, date, GarageActions.CLOSE_GATE));
        viewModel.setGarageGateCloseTime(new Date());
    }

    public void onGarageLightsClick()
    {
        Date date = new Date();
        String id = FirebaseAuth.getInstance().getUid(); //TODO ASK ABOUT THIS, every time i get it form the viewmodel i get exceptions

        if (!isLightOn) {
            viewModel.garageAction(new GarageAction(id, date, GarageActions.LIGHTS_ON));
            long time = date.getTime();
            viewModel.setGarageLightOffTime(new Date(time + ((long) lightCloseTimer * 60 * 1000))); //TODO: change to get from livedata
        } else {
            viewModel.garageAction(new GarageAction(id, date, GarageActions.LIGHTS_OFF));
            viewModel.setGarageLightOffTime(new Date());
        }
    }

    private void startGateTimer()
    {
        if (gateCountDownTimer != null) {
            gateCountDownTimer.cancel();
        }
        gateCountDownTimer = new CountDownTimer(gateTimeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                gateTimeLeftInMilliseconds = millisUntilFinished;
                updateGateCountdownText();
            }

            @Override
            public void onFinish()
            {
                binding.gateCountDownTimerProgressBar.setProgress(100);
                binding.gateCountDownTimerTextView.setVisibility(View.INVISIBLE);
                binding.gateCountDownTimerImageView.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void startLightTimer()
    {
        if (lightCountDownTimer != null) {
            lightCountDownTimer.cancel();
        }
        lightCountDownTimer = new CountDownTimer(lightTimeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                lightTimeLeftInMilliseconds = millisUntilFinished;
                updateLightCountdownText();
            }

            @Override
            public void onFinish()
            {
                binding.lightCountDownTimerProgressBar.setVisibility(View.INVISIBLE);
                binding.lightCountDownTimerTextView.setVisibility(View.INVISIBLE);
                binding.garageLightsButton.setImageResource(bulb_off);
            }
        }.start();
    }

    private void resetGateTimer()
    {
        if (gateCountDownTimer != null) {
            gateCountDownTimer.cancel();
        }
    }

    private void resetLightTimer()
    {
        if (lightCountDownTimer != null) {
            lightCountDownTimer.cancel();
        }
    }

    private void updateGateCountdownText()
    {
        if (binding != null) {
            int minutes = (int) (gateTimeLeftInMilliseconds / 1000) / 60;
            int seconds = (int) (gateTimeLeftInMilliseconds / 1000) % 60;

            int gateTimer = (gateCloseTimer * 60000);

            int progress = (int) (100 * gateTimeLeftInMilliseconds / gateTimer);

            String timeLeftText = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

            binding.gateCountDownTimerProgressBar.setProgress(progress);
            binding.gateCountDownTimerTextView.setText(timeLeftText);
        }
    }

    private void updateLightCountdownText()
    {
        if (binding != null) {
            int minutes = (int) (lightTimeLeftInMilliseconds / 1000) / 60;
            int seconds = (int) (lightTimeLeftInMilliseconds / 1000) % 60;

            int lightTimer = (lightCloseTimer * 60000);

            int progress = (int) (100 * lightTimeLeftInMilliseconds / lightTimer);
            System.out.println(progress);
            String timeLeftText = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

            binding.lightCountDownTimerProgressBar.setProgress(progress);
            binding.lightCountDownTimerTextView.setText(timeLeftText);
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        if (gateCountDownTimer != null) {
            gateCountDownTimer.cancel();
        }
        if (lightCountDownTimer != null) {
            lightCountDownTimer.cancel();
        }
        binding = null;
    }
}