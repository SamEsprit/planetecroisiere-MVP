package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Advantage implements Serializable {

    /**
     * Default constructor
     */
    public Advantage() {
    }

    public Advantage(String name, Integer picto) {
        this.name = name;
        this.picto = picto;
    }

    /**
     * 
     */
    @SerializedName("name")
    private String name;

    /**
     * 
     */
    @SerializedName("longText")
    private String longText;

    /**
     *
     */
    @SerializedName("picto")
    private Integer picto;

    /**
     * 
     */
    public Date createdAt;

    /**
     * 
     */
    public Date updatedAt;

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

    public Integer getPicto() {
        return picto;
    }

    public void setPicto(Integer picto) {
        this.picto = picto;
    }
}