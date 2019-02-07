package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class TypeCabine implements Serializable {

    /**
     * Default constructor
     */
    public TypeCabine() {
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
    private String description;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TypeCabine{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}