package tn.leaderscodes.planetecroisiere.viewholders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;

import tn.leaderscodes.planetecroisiere.R;

public class DestinationViewHolder extends RecyclerView.ViewHolder {
    TextView destination_name_txt;
    private MaterialRippleLayout lyt_parent;
    private ImageView destination_img;
    public DestinationViewHolder(View itemView) {
        super(itemView);
        destination_name_txt = itemView.findViewById(R.id.destination_name_txt);
        lyt_parent = itemView.findViewById(R.id.lyt_parent);
        destination_img = itemView.findViewById(R.id.destination_img);
    }

    public TextView getDestination_name_txt() {
        return destination_name_txt;
    }

    public MaterialRippleLayout getLyt_parent() {
        return lyt_parent;
    }

    public ImageView getDestination_img() {
        return destination_img;
    }
}
