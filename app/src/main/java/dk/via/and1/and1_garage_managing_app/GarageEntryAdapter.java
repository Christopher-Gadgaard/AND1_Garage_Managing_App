package dk.via.and1.and1_garage_managing_app;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GarageEntryAdapter extends RecyclerView.Adapter<GarageEntryAdapter.ViewHolder>
{
    List<GarageEntry> garageEntries;

    public GarageEntryAdapter(List<GarageEntry> garageEntries)
    {
        this.garageEntries = garageEntries;
    }

    @NonNull
    @Override
    public GarageEntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_garage_entry,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GarageEntryAdapter.ViewHolder holder, int position)
    {
        holder.name.setText(garageEntries.get(position).getUser().getFirstName()+" "+garageEntries.get(position).getUser().getLastName());
        holder.email.setText(garageEntries.get(position).getUser().getEmail());
        holder.phoneNo.setText(garageEntries.get(position).getUser().getPhoneNo());
        holder.licensePlateNo.setText(garageEntries.get(position).getUser().getLicensePlate());

        String tempTime = new SimpleDateFormat("h:mm a").format(garageEntries.get(position).getDate());
        holder.time.setText(tempTime);
    }

    @Override
    public int getItemCount()
    {
        return garageEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
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
