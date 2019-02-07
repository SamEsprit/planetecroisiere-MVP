package tn.leaderscodes.planetecroisiere.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.viewholders.CompanyBoatViewHolder;

public class BoatCompanyAdapter extends RecyclerView.Adapter<CompanyBoatViewHolder> {

    private List<Boat> boats;
    Context context;

    public BoatCompanyAdapter(Context context) {
        super();
        this.context=context;
        this.boats = new ArrayList<>();
    }

    public void setmItems(List<Boat> boats) {
        this.boats.clear();
        this.boats = boats;
    }

    public void addData(Boat boat) {
        boats.add(0, boat);
        notifyDataSetChanged();
    }

    public void clear() {
        boats.clear();
        notifyDataSetChanged();
    }

    private BoatCompanyAdapter.OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, Boat obj, int position);
    }
    public void setOnItemClickListener(final BoatCompanyAdapter.OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }


    @NonNull
    @Override
    public CompanyBoatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_company_boat, parent, false);
        return new CompanyBoatViewHolder(v,context);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyBoatViewHolder holder, int position) {
        Boat boat = boats.get(position);
        holder.getBoat_name_txt().setText(boat.getName());
        holder.setImage_boat_img("https://www.australis.com/site/wp-content/uploads/2016/08/stella-nuestro-barco-1.jpg");
        holder.getLyt_parent().setOnClickListener(view -> {
            if( mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(view, boat, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return boats.size();
    }
}
