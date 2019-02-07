package tn.leaderscodes.planetecroisiere.remote.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BoatIdRequest implements Serializable {

    @SerializedName("boat")
    private String boat;

    public BoatIdRequest(String boat) {
        this.boat = boat;
    }
}
