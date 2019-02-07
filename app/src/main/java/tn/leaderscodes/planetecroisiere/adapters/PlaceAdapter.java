package tn.leaderscodes.planetecroisiere.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.remote.entities.StopOver;
import tn.leaderscodes.planetecroisiere.viewholders.PlaceViewHolder;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

    private List<StopOver> stopOvers;
    Context context;

    public PlaceAdapter(Context context) {
        super();
        this.context = context;
        this.stopOvers = new ArrayList<>();
    }

    public void setmItems(List<StopOver> stopOvers) {
        this.stopOvers.clear();
        this.stopOvers = stopOvers;
    }

    public void addData(StopOver stopOver) {
        stopOvers.add(0, stopOver);
        notifyDataSetChanged();
    }

    public void clear() {
        stopOvers.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_places, parent, false);
        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        StopOver stopOver = stopOvers.get(position);
        holder.getName_txt().setText(stopOver.getOrder() + " - " + stopOver.getPlace().getName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.getPlace_desc().setText(Html.fromHtml(stopOver.getPlace().getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.getPlace_desc().setText(Html.fromHtml(stopOver.getPlace().getDescription()));
        }
        holder.getName_txt().setOnClickListener(v -> holder.getExpandableLayout().toggle());
        holder.getButton().setOnClickListener(v -> holder.getExpandableLayout().toggle());
        holder.getArrival_time().setText(stopOver.getArrivalTime());
        holder.getDeparture_time().setText(stopOver.getDepartureTime());
        Glide.with(context).load("https://storage.gra1.cloud.ovh.net/v1/AUTH_5f5451bc9b2b421b8a5e21db942a5510/planete-croisiere/uploads/boat/express-cotier_7468.jpg").into(holder.getPlace_img());
    }

    @Override
    public int getItemCount() {
        return stopOvers.size();
    }
}
