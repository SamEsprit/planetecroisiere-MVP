package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class DeparturesDates implements Serializable {

    /**
     * Default constructor
     */
    public DeparturesDates() {
    }

    /**
     * 
     */
    @SerializedName("date")
    private String Date;

    @SerializedName("pricingList")
    private List<Pricing> pricingList;

    public DeparturesDates(String date) {
        Date = date;
        this.pricingList = new ArrayList<>();
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public List<Pricing> getPricingList() {
        return pricingList;
    }

    public void setPricingList(List<Pricing> pricingList) {
        this.pricingList = pricingList;
    }
}