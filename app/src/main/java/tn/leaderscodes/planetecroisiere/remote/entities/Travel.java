package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 *
 */
public class Travel implements Serializable {

    @SerializedName("_id")
    private String _id;
    @SerializedName("cruise")
    private Cruise cruise;

    @SerializedName("departuresDates")
    private List<DeparturesDates> departuresDates;
    /**
     *
     */
    private Date createdAt;

    /**
     *
     */
    private Date updatedAt;

    /**
     *
     */
    private String source;

    public Cruise getCruise() {
        return cruise;
    }

    public void setCruise(Cruise cruise) {
        this.cruise = cruise;
    }

    public List<DeparturesDates> getDeparturesDates() {
        return departuresDates;
    }

    public void setDeparturesDates(List<DeparturesDates> departuresDates) {
        this.departuresDates = departuresDates;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "cruise=" + cruise +
                ", departuresDates=" + departuresDates +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", source='" + source + '\'' +
                '}';
    }
}