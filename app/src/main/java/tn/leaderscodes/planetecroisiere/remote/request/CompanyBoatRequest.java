package tn.leaderscodes.planetecroisiere.remote.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyBoatRequest implements Serializable {
    @SerializedName("cruiseLine")
    private String cruiseLine;

    public CompanyBoatRequest(String cruiseLine) {
        this.cruiseLine = cruiseLine;
    }

    public String getCruiseLine() {
        return cruiseLine;
    }

    public void setCruiseLine(String cruiseLine) {
        this.cruiseLine = cruiseLine;
    }
}
