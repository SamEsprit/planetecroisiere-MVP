package tn.leaderscodes.planetecroisiere.viewholders;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tn.leaderscodes.planetecroisiere.R;

public class HeaderTarifViewHolder extends RecyclerView.ViewHolder {
    private TextView tarif_header_txt;
    private ConstraintLayout lyt_parent;
    public HeaderTarifViewHolder(View itemView) {
        super(itemView);
        tarif_header_txt=itemView.findViewById(R.id.tarif_header_txt);
        lyt_parent = itemView.findViewById(R.id.lyt_parent);
    }

    public TextView getTarif_header_txt() {
        return tarif_header_txt;
    }

    public ConstraintLayout getLyt_parent() {
        return lyt_parent;
    }
}
