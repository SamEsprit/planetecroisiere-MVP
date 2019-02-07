package tn.leaderscodes.planetecroisiere.remote.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReservationRequest implements Serializable {

    @SerializedName("date")
    private String date;

    @SerializedName("travel")
    private String travel_id;

    @SerializedName("adulte")
    private Integer adulte;

    @SerializedName("child")
    private Integer child;

    @SerializedName("contenu")
    private String contenu;

    @SerializedName("price")
    private float price;

    @SerializedName("cabineCategorie")
    private String cabineCategorie ;

    public String getTravel_id() {
        return travel_id;
    }

    public void setTravel_id(String travel_id) {
        this.travel_id = travel_id;
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

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "travel_id='" + travel_id + '\'' +
                ", adulte=" + adulte +
                ", child=" + child +
                ", contenu='" + contenu + '\'' +
                ", price='" + price + '\'' +
                ", cabineCategorie='" + cabineCategorie + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
