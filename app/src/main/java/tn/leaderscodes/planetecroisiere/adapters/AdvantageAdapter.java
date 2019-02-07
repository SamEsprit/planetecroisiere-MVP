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
import tn.leaderscodes.planetecroisiere.remote.entities.Activite;
import tn.leaderscodes.planetecroisiere.remote.entities.Advantage;
import tn.leaderscodes.planetecroisiere.viewholders.ActivityViewHolder;
import tn.leaderscodes.planetecroisiere.viewholders.AdvantagesViewHolder;

public class AdvantageAdapter extends RecyclerView.Adapter<AdvantagesViewHolder> {

    private List<Advantage> advantages;
    Context context;

    public AdvantageAdapter(Context context) {
        super();
        this.context=context;
        this.advantages = new ArrayList<>();
    }

    public void setmItems(List<Advantage> advantages) {
        this.advantages.clear();
        this.advantages = advantages;
    }

    public void addData(Advantage advantage) {
        advantages.add(0, advantage);
        notifyDataSetChanged();
    }

    public void clear() {
        advantages.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AdvantagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_advantage, parent, false);
        return new AdvantagesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvantagesViewHolder holder, int position) {
        Advantage advantage = advantages.get(position);
        holder.getName_txt().setText(advantage.getName());
        Glide.with(context).load(advantage.getPicto()).into(holder.getLongText_txt());
    }

    @Override
    public int getItemCount() {
        return advantages.size();
    }
}
