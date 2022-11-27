package dk.via.and1.and1_garage_managing_app.ui.my.account;

import android.os.Bundle;
import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.via.and1.and1_garage_managing_app.data.user.User;
import dk.via.and1.and1_garage_managing_app.databinding.FragmentMyAccountBinding;

public class MyAccountFragment extends Fragment {

    private FragmentMyAccountBinding binding;
    private MyAccountViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentMyAccountBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(MyAccountViewModel.class);
        viewModel.init();

       viewModel.getUser().observe(getViewLifecycleOwner(),user -> {
            if (user != null)
            {
                binding.firstNameEditText.setText(user.getFirstName());
                binding.lastNameEditText.setText(user.getLastName());
                binding.emailEditText.setText(user.getEmail());
                binding.phoneEditText.setText(user.getPhoneNo());
                binding.licensePlateEditText.setText(user.getLicensePlate());
            }
        });

       binding.updateButton.setOnClickListener(v -> {
           String firstName = binding.firstNameEditText.getText().toString();
           String lastName = binding.lastNameEditText.getText().toString();
           String email = binding.emailEditText.getText().toString();
           String phoneNo = binding.phoneEditText.getText().toString();
           String licensePlate = binding.licensePlateEditText.getText().toString();

           User user = new User(firstName, lastName, email, phoneNo, licensePlate, false);
           viewModel.updateUser(user);
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