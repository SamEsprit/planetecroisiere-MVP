package tn.leaderscodes.planetecroisiere.viewholders;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.balysv.materialripple.MaterialRippleLayout;
import tn.leaderscodes.planetecroisiere.R;

public class CompaniesViewHolder extends RecyclerView.ViewHolder {
    private TextView company_name_txt;
    private MaterialRippleLayout lyt_parent;
    private ImageView company_img;


    public CompaniesViewHolder(View itemView) {
        super(itemView);
        company_name_txt = itemView.findViewById(R.id.company_name_txt);
        lyt_parent = itemView.findViewById(R.id.lyt_parent);
        company_img = itemView.findViewById(R.id.company_img);
    }

    public TextView getCompany_name_txt() {
        return company_name_txt;
    }

    public MaterialRippleLayout getLyt_parent() {
        return lyt_parent;
    }

    public ImageView getCompany_img() {
        return company_img;
    }
}
