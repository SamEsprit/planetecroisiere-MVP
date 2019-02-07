package tn.leaderscodes.planetecroisiere.viewholders;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;

import tn.leaderscodes.planetecroisiere.R;

public class TarifViewHolder extends RecyclerView.ViewHolder {
    TextView tarif_description,tarif;
    private ConstraintLayout lyt_parent;
    public TarifViewHolder(View itemView) {
        super(itemView);
        tarif_description = itemView.findViewById(R.id.tarif_description);
        tarif = itemView.findViewById(R.id.tarif);
        lyt_parent = itemView.findViewById(R.id.lyt_parent);

    }

    public TextView getTarif_description() {
        return tarif_description;
    }

    public void setTarif_description(TextView tarif_description) {
        this.tarif_description = tarif_description;
    }

    public TextView getTarif() {
        return tarif;
    }

    public void setTarif(TextView tarif) {
        this.tarif = tarif;
    }

    public ConstraintLayout getLyt_parent() {
        return lyt_parent;
    }

    public void setLyt_parent(ConstraintLayout lyt_parent) {
        this.lyt_parent = lyt_parent;
    }
}
