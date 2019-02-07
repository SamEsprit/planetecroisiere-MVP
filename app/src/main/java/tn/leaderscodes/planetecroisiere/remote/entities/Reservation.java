package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import tn.leaderscodes.planetecroisiere.fragments.TravelTarifFragment;

public class Reservation implements Serializable {

    @SerializedName("date")
    private String date;

    @SerializedName("travel")
    private Travel travel;

    @SerializedName("adulte")
    private Integer adulte;

    @SerializedName("child")
    private Integer child;

    @SerializedName("contenu")
    private String contenu;

    @SerializedName("price")
    private float price;

    @SerializedName("cabineCategorie")
    private String cabineCategorie;

    @SerializedName("status")
    private Integer status;

    @SerializedName("user")
    private User user;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Integer getAdulte() {
        return adulte;
    }

    public void setAdulte(Integer adulte) {
        this.adulte = adulte;
    }

    public Integer getChild() {
        return child;
    }

    public void setChild(Integer child) {
        this.child = child;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCabineCategorie() {
        return cabineCategorie;
    }

    public void setCabineCategorie(String cabineCategorie) {
        this.cabineCategorie = cabineCategorie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
