package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 */
public class CategorieCabine implements Serializable {

    /**
     * Default constructor
     */
    public CategorieCabine() {
    }

    @SerializedName("code")
    private String code;

    @SerializedName("description")
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}