package tn.leaderscodes.planetecroisiere.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.remote.entities.StopOver;
import tn.leaderscodes.planetecroisiere.remote.entities.TypeCabine;
import tn.leaderscodes.planetecroisiere.viewholders.CabinsViewHolder;
import tn.leaderscodes.planetecroisiere.viewholders.PlaceViewHolder;

public class CabinsAdapter extends RecyclerView.Adapter<CabinsViewHolder> {

    private List<TypeCabine> typeCabines;
    Context context;

    public CabinsAdapter(Context context) {
        super();
        this.context = context;
        this.typeCabines = new ArrayList<>();
    }

    public void setmItems(List<TypeCabine> typeCabines) {
        Log.d("TAG", "showTypeCabine: "+typeCabines.toString());
        this.typeCabines.clear();
        this.typeCabines = typeCabines;
    }

    public void addData(TypeCabine TypeCabine) {
        typeCabines.add(0, TypeCabine);
        notifyDataSetChanged();
    }

    public void clear() {
        typeCabines.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CabinsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cabins, parent, false);
        return new CabinsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CabinsViewHolder holder, int position) {
        TypeCabine typeCabine = typeCabines.get(position);
        holder.getName_txt().setText(typeCabine.getName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.getPlace_desc().setText(Html.fromHtml(typeCabine.getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.getPlace_desc().setText(Html.fromHtml(typeCabine.getDescription()));
        }
        holder.getName_txt().setOnClickListener(v -> holder.getExpandableLayout().toggle());
        holder.getButton().setOnClickListener(v -> holder.getExpandableLayout().toggle());
        Glide.with(context).load("https://storage.gra1.cloud.ovh.net/v1/AUTH_5f5451bc9b2b421b8a5e21db942a5510/planete-croisiere/uploads/boat/express-cotier_7468.jpg").into(holder.getPlace_img());
    }

    @Override
    public int getItemCount() {
        return typeCabines.size();
    }
}
