package tn.leaderscodes.planetecroisiere.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.activities.DetailTravel;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.viewholders.TravelViewHolder;

public class TravelAdapter extends RecyclerView.Adapter<TravelViewHolder> {
    private List<Travel> travels;
    Context context;

    public TravelAdapter(Context context) {
        super();
        this.context = context;
        travels = new ArrayList<>();
    }

    public void setmItems(List<Travel> travels) {
        this.travels.clear();
        this.travels = travels;
    }

    public void addData(Travel travel) {
        travels.add(0, travel);
        notifyDataSetChanged();
    }

    public void clear() {
        travels.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_travel, parent, false);
        return new TravelViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder holder, int position) {
        Travel travel = travels.get(position);
        if (travel.getCruise().getGeo().getSubRegion() != null)
            holder.getDestination_name().setText(travel.getCruise().getGeo().getSubRegion().getName());
        else
            holder.getDestination_name().setText(travel.getCruise().getGeo().getName());
        holder.getTitle_travel().setText(travel.getCruise().getName());
        holder.getTravel_duree().setText((travel.getCruise().getCruiseLength() + 1) + " /" + travel.getCruise().getCruiseLength());
        holder.getTrave_depart().setText(travel.getCruise().getPlace().getName());
        holder.getFirst_departure_date().setText(travel.getDeparturesDates().get(0).getDate());
        holder.getPrice_travel().setText(travel.getDeparturesDates().get(0).getPricingList().get(0).getPricingList().get(0).getPrice() + " €");
        holder.getCruiseLine_title().setText(travel.getCruise().getCruiseLine().getName());
        holder.getFc().setOnClickListener(v -> holder.getFc().toggle(false));


        holder.getTravel_title_content().setText(travel.getCruise().getName());
        if (travel.getCruise().getGeo().getSubRegion() != null)
            holder.getTravel_destination_content().setText(travel.getCruise().getGeo().getSubRegion().getName());
        else
            holder.getTravel_destination_content().setText(travel.getCruise().getGeo().getName());
        holder.getTravel_duree_content().setText((travel.getCruise().getCruiseLength() + 1) + " /" + travel.getCruise().getCruiseLength());
        holder.getTravel_departdate_content().setText(travel.getDeparturesDates().get(0).getDate());
        holder.getTravel_depart_content().setText(travel.getCruise().getPlace().getName());
        holder.getPrice_content().setText(holder.getPrice_content().getText() + " \n" + travel.getDeparturesDates().get(0).getPricingList().get(0).getPricingList().get(0).getPrice() + " €");

        holder.getCruiseLine_content().setText(travel.getCruise().getCruiseLine().getName());
        Glide.with(context).load("https://storage.gra1.cloud.ovh.net/v1/AUTH_5f5451bc9b2b421b8a5e21db942a5510/planete-croisiere/uploads/place/montego-bay-jamaique_219.jpg").into(holder.getTravel_image());
        String itineraire = "";
        for (int i = 0; i < travel.getCruise().getItinerary().getStopOvers().size(); i++) {
            if (i == travel.getCruise().getItinerary().getStopOvers().size() - 1) {
                if (!itineraire.contains(travel.getCruise().getItinerary().getStopOvers().get(i).getPlace().getName()))
                    itineraire += travel.getCruise().getItinerary().getStopOvers().get(i).getPlace().getName() + " .";
            } else {
                if (travel.getCruise().getItinerary().getStopOvers().get(i).getPlace() != null)
                    itineraire += travel.getCruise().getItinerary().getStopOvers().get(i).getPlace().getName() + ", ";
            }
        }
        holder.getTravel_itinerary_content().setText(itineraire);

        holder.getShow_plus().setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailTravel.class);
            intent.putExtra("Travel",travel);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return travels.size();
    }
}
