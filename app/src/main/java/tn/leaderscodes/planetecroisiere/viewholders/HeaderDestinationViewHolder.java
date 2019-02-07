package tn.leaderscodes.planetecroisiere.viewholders;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;



import tn.leaderscodes.planetecroisiere.R;

public class HeaderDestinationViewHolder extends RecyclerView.ViewHolder {
    private TextView destination_header_txt;
    private ConstraintLayout lyt_parent;
    public HeaderDestinationViewHolder(View itemView) {
        super(itemView);
        destination_header_txt=itemView.findViewById(R.id.destination_header_txt);
        lyt_parent = itemView.findViewById(R.id.lyt_parent);
    }

    public TextView getDestination_header_txt() {
        return destination_header_txt;
    }

    public ConstraintLayout getLyt_parent() {
        return lyt_parent;
    }
}
