package tn.leaderscodes.planetecroisiere.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import tn.leaderscodes.planetecroisiere.R;

public class PlaceViewHolder extends RecyclerView.ViewHolder {


    //Header
    private TextView name_txt;
    private TextView place_desc;
    private TextView departure_time,arrival_time;
    private ExpandableLinearLayout expandableLayout;
    private ImageView place_img;
    private RelativeLayout button;


    public PlaceViewHolder(View itemView) {
        super(itemView);
        name_txt=itemView.findViewById(R.id.name_txt);
        place_desc=itemView.findViewById(R.id.place_desc);
        expandableLayout=itemView.findViewById(R.id.expandableLayout);
        place_img=itemView.findViewById(R.id.place_img);
        departure_time=itemView.findViewById(R.id.departure_time);
        arrival_time=itemView.findViewById(R.id.arrival_time);
        button=itemView.findViewById(R.id.button);
    }

    public TextView getName_txt() {
        return name_txt;
    }

    public void setName_txt(TextView name_txt) {
        this.name_txt = name_txt;
    }

    public TextView getPlace_desc() {
        return place_desc;
    }

    public ExpandableLinearLayout getExpandableLayout() {
        return expandableLayout;
    }

    public ImageView getPlace_img() {
        return place_img;
    }

    public TextView getDeparture_time() {
        return departure_time;
    }

    public TextView getArrival_time() {
        return arrival_time;
    }

    public RelativeLayout getButton() {
        return button;
    }
}
