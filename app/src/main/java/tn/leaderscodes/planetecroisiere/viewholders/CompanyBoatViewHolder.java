package tn.leaderscodes.planetecroisiere.viewholders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;

import tn.leaderscodes.planetecroisiere.R;

public class CompanyBoatViewHolder extends RecyclerView.ViewHolder {
    private static int viewWidth = 0;
    private static int viewHeight = 0;
    private TextView boat_name_txt;
    private ImageView image_boat_img;
    private CardView lyt_parent;
    Context context;

    public CompanyBoatViewHolder(View itemView, Context context) {
        super(itemView);
        this.context=context;
        boat_name_txt = itemView.findViewById(R.id.boat_name_txt);
        image_boat_img = itemView.findViewById(R.id.image_boat_img);
        lyt_parent = itemView.findViewById(R.id.lyt_parent);
    }

    public TextView getBoat_name_txt() {
        return boat_name_txt;
    }

    public ImageView getImage_boat_img() {
        return image_boat_img;
    }

    public CardView getLyt_parent() {
        return lyt_parent;
    }

    public void setImage_boat_img(String image_boat) {
        if (viewWidth == 0) {
            itemView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    itemView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    viewWidth = itemView.getWidth();
                    viewHeight = itemView.getHeight();
                    Glide.with(context).load(image_boat).into(image_boat_img);
                }
            });
        } else
            Glide.with(context).load(image_boat).into(image_boat_img);
    }
}
