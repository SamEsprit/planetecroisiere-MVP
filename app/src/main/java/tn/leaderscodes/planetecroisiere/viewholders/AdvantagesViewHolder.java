package tn.leaderscodes.planetecroisiere.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tn.leaderscodes.planetecroisiere.R;

public class AdvantagesViewHolder extends RecyclerView.ViewHolder {


    //Header
    private TextView name_txt;
    private ImageView picto;


    public AdvantagesViewHolder(View itemView) {
        super(itemView);
        name_txt=itemView.findViewById(R.id.name_txt);
        picto=itemView.findViewById(R.id.picto);

    }

    public TextView getName_txt() {
        return name_txt;
    }

    public void setName_txt(TextView name_txt) {
        this.name_txt = name_txt;
    }

    public ImageView getLongText_txt() {
        return picto;
    }

    public void setLongText_txt(ImageView picto) {
        this.picto = picto;
    }
}
