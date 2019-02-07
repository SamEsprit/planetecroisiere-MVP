package tn.leaderscodes.planetecroisiere.adapters;

import android.app.Activity;
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
import tn.leaderscodes.planetecroisiere.remote.entities.Activite;
import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.viewholders.ActivityViewHolder;
import tn.leaderscodes.planetecroisiere.viewholders.CompanyBoatViewHolder;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {

    private List<Activite> activites;
    Context context;

    public ActivityAdapter(Context context) {
        super();
        this.context=context;
        this.activites = new ArrayList<>();
    }

    public void setmItems(List<Activite> activites) {
        this.activites.clear();
        this.activites = activites;
    }

    public void addData(Activite activite) {
        activites.add(0, activite);
        notifyDataSetChanged();
    }

    public void clear() {
        activites.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_activity, parent, false);
        return new ActivityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        Activite activite = activites.get(position);
        holder.getName_txt().setText(activite.getName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.getLongText_txt().setText(Html.fromHtml(activite.getLongText(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.getLongText_txt().setText(Html.fromHtml(activite.getLongText()));
        }
        holder.getName_txt().setOnClickListener(v -> holder.getExpandableLayout().toggle());
        holder.getButton().setOnClickListener(v -> holder.getExpandableLayout().toggle());

        Glide.with(context).load(activite.getImage()).into(holder.getActivity_img());
    }

    @Override
    public int getItemCount() {
        return activites.size();
    }
}
