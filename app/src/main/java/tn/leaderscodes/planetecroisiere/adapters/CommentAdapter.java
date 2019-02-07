package tn.leaderscodes.planetecroisiere.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.activities.CommentsActivity;
import tn.leaderscodes.planetecroisiere.remote.entities.Activite;
import tn.leaderscodes.planetecroisiere.remote.entities.Commentaire;
import tn.leaderscodes.planetecroisiere.tools.URLS;
import tn.leaderscodes.planetecroisiere.viewholders.ActivityViewHolder;
import tn.leaderscodes.planetecroisiere.viewholders.CommentaireViewHolder;

public class CommentAdapter extends RecyclerView.Adapter<CommentaireViewHolder> {

    private List<Commentaire> commentaires;
    Context context;

    public CommentAdapter(Context context) {
        super();
        this.context = context;
        this.commentaires = new ArrayList<>();
    }

    public void setmItems(List<Commentaire> commentaires) {
        this.commentaires.clear();
        this.commentaires = commentaires;
    }

    public void addData(Commentaire commentaire) {
        commentaires.add(0, commentaire);
        notifyDataSetChanged();
    }

    public void clear() {
        commentaires.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CommentaireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_commentaire, parent, false);
        return new CommentaireViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentaireViewHolder holder, int position) {
        Commentaire commentaire = commentaires.get(position);
        holder.getUsername().setText(commentaire.getUser().getFirstName() + " " + commentaire.getUser().getLastName());
        Glide.with(context).load(URLS.EndPointProfilImg + commentaire.getUser().getImage()).into(holder.getImg_Comm());
        holder.getContenu_commentaire().setText(commentaire.getText());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String d = commentaire.getDate().substring(8, 10) + "/" + commentaire.getDate().substring(5, 7) + "/" + commentaire.getDate().substring(0, 4) + " " + commentaire.getDate().substring(11, 16);
        try {
            Date date = dateFormat.parse(d);
            holder.getDate_comm().setReferenceTime(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return commentaires.size();
    }
}
