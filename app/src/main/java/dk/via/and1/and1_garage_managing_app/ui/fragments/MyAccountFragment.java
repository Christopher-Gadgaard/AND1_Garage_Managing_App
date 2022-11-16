package dk.via.and1.and1_garage_managing_app.ui.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;

import dk.via.and1.and1_garage_managing_app.data.user.UserInfo;
import dk.via.and1.and1_garage_managing_app.databinding.FragmentMyAccountBinding;
import dk.via.and1.and1_garage_managing_app.ui.viewmodels.MyAccountViewModel;

public class MyAccountFragment extends Fragment {

    private FragmentMyAccountBinding binding;
    private MyAccountViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentMyAccountBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(MyAccountViewModel.class);
        viewModel.init();

       viewModel.getUserInfo().observe(getViewLifecycleOwner(),userInfo -> {
            if (userInfo != null)
            {
                System.out.println(userInfo.getFirstName());
                binding.firstNameEditText.setText(userInfo.getFirstName());
                binding.lastNameEditText.setText(userInfo.getLastName());
                binding.emailEditText.setText(userInfo.getEmail());
                binding.phoneEditText.setText(userInfo.getPhoneNo());
                binding.licensePlateEditText.setText(userInfo.getLicensePlate());
            }
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