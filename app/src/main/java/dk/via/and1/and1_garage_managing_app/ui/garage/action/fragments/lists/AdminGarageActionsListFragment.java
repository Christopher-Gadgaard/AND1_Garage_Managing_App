package dk.via.and1.and1_garage_managing_app.ui.garage.action.fragments.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dk.via.and1.and1_garage_managing_app.R;
import dk.via.and1.and1_garage_managing_app.databinding.FragmentGarageActionAdminBinding;

import dk.via.and1.and1_garage_managing_app.databinding.FragmentGarageActionAdminListBinding;
import dk.via.and1.and1_garage_managing_app.ui.garage.action.adapters.AdminGarageActionAdapter;
import dk.via.and1.and1_garage_managing_app.ui.garage.action.adapters.UserGarageActionAdapter;

public class AdminGarageActionsListFragment extends Fragment {

    private FragmentGarageActionAdminListBinding binding;
    private AdminGarageActionsListViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentGarageActionAdminListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.adminGarageActionListRecyclerView);
        recyclerView.hasFixedSize();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);

        viewModel = new ViewModelProvider(this).get(AdminGarageActionsListViewModel.class);

        viewModel.getAdminGarageActions().observe(getViewLifecycleOwner(), garageActions -> {
            AdminGarageActionAdapter adapter = new AdminGarageActionAdapter(garageActions);
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}