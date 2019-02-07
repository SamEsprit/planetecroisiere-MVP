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
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.entities.Reservation;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.viewholders.ReservationViewHolder;
import tn.leaderscodes.planetecroisiere.viewholders.TravelViewHolder;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder> {
    private List<Reservation> reservations;
    Context context;

    public ReservationAdapter(Context context) {
        super();
        this.context = context;
        reservations = new ArrayList<>();
    }

    public void setmItems(List<Reservation> reservations) {
        this.reservations.clear();
        this.reservations = reservations;
    }

    public void addData(Reservation reservation) {
        reservations.add(0, reservation);
        notifyDataSetChanged();
    }

    public void clear() {
        reservations.clear();
        notifyDataSetChanged();
    }

    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, Reservation obj, int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reservation, parent, false);
        return new ReservationViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        holder.getAdulte_nbr().setText(reservation.getAdulte()+"");
        holder.getChild_nbr().setText(reservation.getChild()+"");
        holder.getBoat_txt().setText(reservation.getTravel().getCruise().getBoat().getName()+"");
        holder.getComany_txt().setText(reservation.getTravel().getCruise().getCruiseLine().getName()+"");
        holder.getDepart_date().setText(reservation.getDate());
        holder.getPrice_txt().setText(reservation.getPrice()+" €");
        holder.getTravel_name().setText(reservation.getTravel().getCruise().getName());
        holder.getLyt_parent().setOnClickListener(view -> {
            if( mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(view, reservation, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }
}
