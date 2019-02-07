package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 
 */
public class Price implements Serializable {


    /**
     * 
     */
    @SerializedName("category")
    private CategorieCabine category;

    /**
     * 
     */
    @SerializedName("deckLoc")
    private String deckLoc;

    /**
     * 
     */
    @SerializedName("price")
    private float price;

    /**
     * 
     */
    @SerializedName("flightManfatory")
    private String flightManfatory;

    /**
     * 
     */
    @SerializedName("flaight")
    private String flaight;

    /**
     * 
     */
    @SerializedName("gateway")
    private String gateway;

    /**
     * 
     */
    @SerializedName("flightPrice")
    private float flightPrice;

    public CategorieCabine getCategory() {
        return category;
    }

    public void setCategory(CategorieCabine category) {
        this.category = category;
    }

    public String getDeckLoc() {
        return deckLoc;
    }

    public void setDeckLoc(String deckLoc) {
        this.deckLoc = deckLoc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getFlightManfatory() {
        return flightManfatory;
    }

    public void setFlightManfatory(String flightManfatory) {
        this.flightManfatory = flightManfatory;
    }

    public String getFlaight() {
        return flaight;
    }

    public void setFlaight(String flaight) {
        this.flaight = flaight;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public float getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(float flightPrice) {
        this.flightPrice = flightPrice;
    }

    @Override
    public String toString() {
        return "Price{" +
                "category=" + category +
                ", deckLoc='" + deckLoc + '\'' +
                ", price=" + price +
                ", flightManfatory='" + flightManfatory + '\'' +
                ", flaight='" + flaight + '\'' +
                ", gateway='" + gateway + '\'' +
                ", flightPrice=" + flightPrice +
                '}';
    }
}