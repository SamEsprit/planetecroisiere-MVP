package tn.leaderscodes.planetecroisiere.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.ramotion.foldingcell.FoldingCell;

import tn.leaderscodes.planetecroisiere.R;

public class ActivityViewHolder extends RecyclerView.ViewHolder {


    //Header
    private TextView name_txt;
    private TextView longText_txt;
    private ExpandableLinearLayout expandableLayout;
    private ImageView activity_img;
    private RelativeLayout button;


    public ActivityViewHolder(View itemView) {
        super(itemView);
        name_txt=itemView.findViewById(R.id.name_txt);
        longText_txt=itemView.findViewById(R.id.longText_txt);
        expandableLayout=itemView.findViewById(R.id.expandableLayout);
        activity_img=itemView.findViewById(R.id.activity_img);
        button=itemView.findViewById(R.id.button);

    }

    public TextView getName_txt() {
        return name_txt;
    }

    public void setName_txt(TextView name_txt) {
        this.name_txt = name_txt;
    }

    public TextView getLongText_txt() {
        return longText_txt;
    }

    public void setLongText_txt(TextView longText_txt) {
        this.longText_txt = longText_txt;
    }

    public ExpandableLinearLayout getExpandableLayout() {
        return expandableLayout;
    }

    public ImageView getActivity_img() {
        return activity_img;
    }

    public RelativeLayout getButton() {
        return button;
    }
}
