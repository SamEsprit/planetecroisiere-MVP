package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class SubRegion implements Serializable {


    public SubRegion() {
    }

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("longText")
    private String longText;

    @SerializedName("shortText")
    private String shortText;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private Date updatedAt;

    @SerializedName("subRegion")
    private SubRegion subRegion;

    @SerializedName("subRegionsCorps")
    private List<String> subRegionsCorps;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public SubRegion getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(SubRegion subRegion) {
        this.subRegion = subRegion;
    }

    public List<String> getSubRegionsCorps() {
        return subRegionsCorps;
    }

    public void setSubRegionsCorps(List<String> subRegionsCorps) {
        this.subRegionsCorps = subRegionsCorps;
    }

    @Override
    public String toString() {
        return name ;
    }

    public SubRegion(String name) {
        this.name = name;
    }
}