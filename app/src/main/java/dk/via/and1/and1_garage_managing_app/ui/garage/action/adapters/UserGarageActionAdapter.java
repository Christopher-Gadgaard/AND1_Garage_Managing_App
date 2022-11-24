package dk.via.and1.and1_garage_managing_app.ui.garage.action.adapters;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import dk.via.and1.and1_garage_managing_app.R;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageAction;
import dk.via.and1.and1_garage_managing_app.data.garage.GarageActions;


public class UserGarageActionAdapter extends RecyclerView.Adapter<UserGarageActionAdapter.ViewHolder>
{
    List<GarageAction> garageActions;

    public UserGarageActionAdapter(List<GarageAction> garageActions)
    {
        this.garageActions = garageActions;
    }

    @NonNull
    @Override
    public UserGarageActionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_garage_action_user,parent,false);
        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserGarageActionAdapter.ViewHolder holder, int position)
    {
        GarageAction garageAction = garageActions.get(position);
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

    @Override
    public int getItemCount()
    {
        return garageActions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView time;
        ImageView action;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            time = itemView.findViewById(R.id.timeTextView);
            action = itemView.findViewById(R.id.actionImageVIew);
        }
    }
}
