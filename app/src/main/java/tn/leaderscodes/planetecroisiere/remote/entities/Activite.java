package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Activite implements Serializable {

    /**
     * Default constructor
     */
    public Activite() {
    }

    /**
     * 
     */
    @SerializedName("name")
    private String name;

    /**
     * 
     */
    @SerializedName("description")
    private String longText;

    @SerializedName("image")
    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}