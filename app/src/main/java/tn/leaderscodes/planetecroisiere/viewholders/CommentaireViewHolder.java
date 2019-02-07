package tn.leaderscodes.planetecroisiere.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.curioustechizen.ago.RelativeTimeTextView;

import tn.leaderscodes.planetecroisiere.R;

public class CommentaireViewHolder extends RecyclerView.ViewHolder {


    //Header
    private TextView username;
    private TextView contenu_commentaire;
    private RelativeTimeTextView date_comm;
    private ImageView img_Comm;


    public CommentaireViewHolder(View itemView) {
        super(itemView);
        username=itemView.findViewById(R.id.username);
        date_comm=itemView.findViewById(R.id.date_comm);
        contenu_commentaire=itemView.findViewById(R.id.contenu_commentaire);
        img_Comm=itemView.findViewById(R.id.img_Comm);
    }

    public TextView getUsername() {
        return username;
    }

    public void setUsername(TextView username) {
        this.username = username;
    }

    public RelativeTimeTextView getDate_comm() {
        return date_comm;
    }

    public void setDate_comm(RelativeTimeTextView date_comm) {
        this.date_comm = date_comm;
    }

    public TextView getContenu_commentaire() {
        return contenu_commentaire;
    }

    public void setContenu_commentaire(TextView contenu_commentaire) {
        this.contenu_commentaire = contenu_commentaire;
    }

    public ImageView getImg_Comm() {
        return img_Comm;
    }

    public void setImg_Comm(ImageView img_Comm) {
        this.img_Comm = img_Comm;
    }
}
