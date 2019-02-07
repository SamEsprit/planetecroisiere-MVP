package tn.leaderscodes.planetecroisiere.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import tn.leaderscodes.planetecroisiere.R;

public class CabinsViewHolder extends RecyclerView.ViewHolder {


    //Header
    private TextView name_txt;
    private TextView cabine_desc;
    private ExpandableLinearLayout expandableLayout;
    private ImageView cabin_img;
    private  RelativeLayout button;


    public CabinsViewHolder(View itemView) {
        super(itemView);
        name_txt = itemView.findViewById(R.id.name_txt);
        cabine_desc = itemView.findViewById(R.id.cabine_desc);
        expandableLayout = itemView.findViewById(R.id.expandableLayout);
        cabin_img = itemView.findViewById(R.id.cabin_img);
        button = itemView.findViewById(R.id.button);
    }

    public TextView getName_txt() {
        return name_txt;
    }

    public void setName_txt(TextView name_txt) {
        this.name_txt = name_txt;
    }

    public TextView getPlace_desc() {
        return cabine_desc;
    }

    public ExpandableLinearLayout getExpandableLayout() {
        return expandableLayout;
    }

    public ImageView getPlace_img() {
        return cabin_img;
    }

    public RelativeLayout getButton() {
        return button;
    }
}
