package tn.leaderscodes.planetecroisiere.remote.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import tn.leaderscodes.planetecroisiere.remote.entities.Commentaire;

public class CommentsRequest implements Serializable {


    @SerializedName("comment")
    private Commentaire commentaire;

    public CommentsRequest(Commentaire commentaire) {

        this.commentaire = commentaire;
    }

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }
}
