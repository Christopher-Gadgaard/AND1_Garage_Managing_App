package dk.via.and1.and1_garage_managing_app.ui.my.account;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import dk.via.and1.and1_garage_managing_app.R;
import dk.via.and1.and1_garage_managing_app.data.user.User;
import dk.via.and1.and1_garage_managing_app.databinding.DialogChangePasswordBinding;
import dk.via.and1.and1_garage_managing_app.databinding.FragmentMyAccountBinding;
import dk.via.and1.and1_garage_managing_app.ui.main.MainActivity;

public class MyAccountFragment extends Fragment {

    private FragmentMyAccountBinding binding;
    private DialogChangePasswordBinding dialogBinding;
    private MyAccountViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentMyAccountBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(MyAccountViewModel.class);

        //Making a toast based on the result of the update or password change;
        viewModel.getResult().observe(getViewLifecycleOwner(), result-> Toast.makeText(binding.getRoot().getContext(),result,Toast.LENGTH_LONG).show());


        //Setting the textviews to the current user's information
        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                binding.firstNameEditText.setText(user.getFirstName());
                binding.lastNameEditText.setText(user.getLastName());
                binding.emailEditText.setText(user.getEmail());
                binding.phoneEditText.setText(user.getPhoneNo());
                binding.licensePlateEditText.setText(user.getLicensePlate());
            }
        });

        //Setting the onclicklistener for the change password button
        binding.updateButton.setOnClickListener(v -> {
            String firstName = binding.firstNameEditText.getText().toString();
            String lastName = binding.lastNameEditText.getText().toString();
            String email = binding.emailEditText.getText().toString();
            String phoneNo = binding.phoneEditText.getText().toString();
            String licensePlate = binding.licensePlateEditText.getText().toString();

            User user = new User(firstName, lastName,  phoneNo, email,licensePlate, false);
            viewModel.updateUser(user);

            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        });

        //Setting the onclicklistener for the change password button
        binding.changePasswordButton.setOnClickListener(v -> {
            dialogBinding = DialogChangePasswordBinding.inflate(getLayoutInflater());
            final AlertDialog.Builder dialog = new AlertDialog.Builder(binding.getRoot().getContext(), R.style.AlertDialog);
            dialog.setView(dialogBinding.getRoot());
            dialog.setTitle("Change password");
            dialog.setMessage("Enter your new password");
            dialog.setPositiveButton("Update", (dialog1, which) -> {
                String password = dialogBinding.passwordEditText.getText().toString();
                viewModel.changePassword(password);
            });
            dialog.setNegativeButton("Cancel", (dialog12, which) -> dialog12.dismiss());
            dialog.create().show();
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