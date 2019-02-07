package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 *
 */
public class Publication implements Serializable {

    /**
     * Default constructor
     */
    public Publication() {
    }

    @SerializedName("_id")
    private String _id;

    @SerializedName("commentaires")
    private List<Commentaire> commentaires;

    @SerializedName("user")
    private User user;

    @SerializedName("createdAt")
    private String date;


    @SerializedName("titre")
    private String titre;

    @SerializedName("description")
    private String contenu;


    @SerializedName("image")
    private String image;
    /**
     *
     */
    @SerializedName("liked")
    private boolean liked;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "_id='" + _id + '\'' +
                ", commentaires=" + commentaires +
                ", user=" + user +
                ", date='" + date + '\'' +
                ", titre='" + titre + '\'' +
                ", contenu='" + contenu + '\'' +
                ", image='" + image + '\'' +
                ", liked=" + liked +
                '}';
    }
}