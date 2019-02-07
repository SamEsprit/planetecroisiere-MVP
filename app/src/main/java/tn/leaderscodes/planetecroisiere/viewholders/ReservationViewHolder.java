package tn.leaderscodes.planetecroisiere.viewholders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import tn.leaderscodes.planetecroisiere.R;

public class ReservationViewHolder extends RecyclerView.ViewHolder {


    //Header
    private TextView travel_name,depart_date,adulte_nbr,child_nbr,comany_txt,boat_txt,price_txt;
    private CardView lyt_parent;



    public ReservationViewHolder(View itemView) {
        super(itemView);
        travel_name=itemView.findViewById(R.id.travel_name);
        depart_date=itemView.findViewById(R.id.depart_date);
        adulte_nbr=itemView.findViewById(R.id.adulte_nbr);
        child_nbr=itemView.findViewById(R.id.child_nbr);
        comany_txt=itemView.findViewById(R.id.comany_txt);
        boat_txt=itemView.findViewById(R.id.boat_txt);
        price_txt=itemView.findViewById(R.id.price_txt);
        lyt_parent=itemView.findViewById(R.id.lyt_parent);

    }

    public TextView getTravel_name() {
        return travel_name;
    }

    public TextView getDepart_date() {
        return depart_date;
    }

    public TextView getAdulte_nbr() {
        return adulte_nbr;
    }

    public TextView getChild_nbr() {
        return child_nbr;
    }

    public TextView getComany_txt() {
        return comany_txt;
    }

    public TextView getBoat_txt() {
        return boat_txt;
    }

    public TextView getPrice_txt() {
        return price_txt;
    }

    public CardView getLyt_parent() {
        return lyt_parent;
    }
}
