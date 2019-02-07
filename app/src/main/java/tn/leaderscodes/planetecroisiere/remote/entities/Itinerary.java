package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Itinerary implements Serializable {

    @SerializedName("code")
    private String code;

    @SerializedName("stopovers")
    private List<StopOver> stopOvers;

    private Date createdAt;

    private Date updatedAt;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<StopOver> getStopOvers() {
        return stopOvers;
    }

    public void setStopOvers(List<StopOver> stopOvers) {
        this.stopOvers = stopOvers;
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "code='" + code + '\'' +
                ", stopOvers=" + stopOvers +
                '}';
    }
}