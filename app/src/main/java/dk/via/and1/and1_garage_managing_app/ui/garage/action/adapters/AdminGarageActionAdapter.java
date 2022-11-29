package dk.via.and1.and1_garage_managing_app.ui.garage.action.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dk.via.and1.and1_garage_managing_app.R;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageAction;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageActions;
import dk.via.and1.and1_garage_managing_app.data.user.User;
import dk.via.and1.and1_garage_managing_app.data.user.UserRepository;
import dk.via.and1.and1_garage_managing_app.ui.garage.action.fragments.lists.AdminGarageActionsListFragment;
import dk.via.and1.and1_garage_managing_app.ui.garage.action.fragments.lists.AdminGarageActionsListViewModel;

public class AdminGarageActionAdapter extends RecyclerView.Adapter<AdminGarageActionAdapter.ViewHolder>
{
    List<GarageAction> garageActions;
    AdminGarageActionsListViewModel viewModel;


    public AdminGarageActionAdapter(List<GarageAction> garageActions)
    {
        this.garageActions = garageActions;
    }

    @NonNull
    @Override
    public AdminGarageActionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_garage_action_admin,parent,false);
        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdminGarageActionAdapter.ViewHolder holder, int position)
    {
        GarageAction garageAction = garageActions.get(position);
        FirebaseDatabase.getInstance("https://and1-garage-managing-app-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users").child(garageAction.getUserId()).get().addOnCompleteListener(task -> { //TODO MOVE THIS TO VIEWMODEL/REPOSITORY
            if (task.isSuccessful())
            {
                User user = task.getResult().getValue(User.class);
                holder.name.setText(user.getFirstName() + " " + user.getLastName());
                holder.email.setText(user.getEmail());
                holder.name.setText(user.getFirstName() + " " + user.getLastName());
                holder.phoneNo.setText(user.getPhoneNo());
                holder.licensePlateNo.setText(user.getLicensePlate());
                Date date = garageAction.getDate();
                @SuppressLint("SimpleDateFormat") String sDate = new SimpleDateFormat("hh:mm a dd/MM/yyyy").format(date);
                holder.time.setText(sDate);

                if(garageAction.getGarageActions() == GarageActions.OPEN_GATE)
                {
                    holder.action.setImageResource(R.drawable.unlocked);
                }
                if(garageAction.getGarageActions() == GarageActions.CLOSE_GATE)
                {
                    holder.action.setImageResource(R.drawable.locked);
                }
                if(garageAction.getGarageActions() == GarageActions.LIGHTS_OFF)
                {
                    holder.action.setImageResource(R.drawable.bulb_off);
                }
                if(garageAction.getGarageActions() == GarageActions.LIGHTS_ON)
                {
                    holder.action.setImageResource(R.drawable.bulb_on);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return garageActions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView name;
        TextView email;
        TextView phoneNo;
        TextView time;
        TextView licensePlateNo;
        ImageView action;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            email = itemView.findViewById(R.id.emailTextview);
            phoneNo = itemView.findViewById(R.id.phoneNoTextView);
            time = itemView.findViewById(R.id.timeTextView);
            licensePlateNo = itemView.findViewById(R.id.licensePlateNoTextView);
            action = itemView.findViewById(R.id.actionImageVIew);
        }
    }
}
