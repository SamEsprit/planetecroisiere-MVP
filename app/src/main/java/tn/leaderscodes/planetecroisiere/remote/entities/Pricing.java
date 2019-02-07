package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Pricing implements Serializable {



    @SerializedName("categoryName")
    private String category;

    @SerializedName("prices")
    private List<Price> pricingList;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Price> getPricingList() {
        return pricingList;
    }

    public void setPricingList(List<Price> pricingList) {
        this.pricingList = pricingList;
    }

    @Override
    public String toString() {
        return "Pricing{" +
                "category='" + category + '\'' +
                ", pricingList=" + pricingList +
                '}';
    }
}