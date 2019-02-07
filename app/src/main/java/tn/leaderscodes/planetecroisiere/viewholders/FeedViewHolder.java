package tn.leaderscodes.planetecroisiere.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.curioustechizen.ago.RelativeTimeTextView;

import tn.leaderscodes.planetecroisiere.R;

public class FeedViewHolder extends RecyclerView.ViewHolder {

    private ImageView user_image,photo_content;
    private TextView user_name,text_content;
    private ImageView bt_like,bt_comment;
    private RelativeTimeTextView text_date;
    public FeedViewHolder(View itemView) {
        super(itemView);
        user_image=itemView.findViewById(R.id.user_image);
        photo_content=itemView.findViewById(R.id.photo_content);
        user_name=itemView.findViewById(R.id.user_name);
        text_date=itemView.findViewById(R.id.text_date);
        text_content=itemView.findViewById(R.id.text_content);
        bt_like=itemView.findViewById(R.id.bt_like);
        bt_comment=itemView.findViewById(R.id.bt_comment);
    }

    public ImageView getUser_image() {
        return user_image;
    }

    public ImageView getPhoto_content() {
        return photo_content;
    }

    public TextView getUser_name() {
        return user_name;
    }

    public RelativeTimeTextView getText_date() {
        return text_date;
    }

    public TextView getText_content() {
        return text_content;
    }

    public ImageView getBt_like() {
        return bt_like;
    }

    public ImageView getBt_comment() {
        return bt_comment;
    }
}
