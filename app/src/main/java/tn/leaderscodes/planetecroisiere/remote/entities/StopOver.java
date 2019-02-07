package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class StopOver implements Serializable {

    /**
     * Default constructor
     */
    public StopOver() {
    }
    @SerializedName("order")
    private Integer order;

    @SerializedName("place")
    private Place place;

    @SerializedName("departureTime")
    private String departureTime;

    @SerializedName("arrivalTime")
    private String arrivalTime;


    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "StopOver{" +
                "order=" + order +
                ", place=" + place +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }
}