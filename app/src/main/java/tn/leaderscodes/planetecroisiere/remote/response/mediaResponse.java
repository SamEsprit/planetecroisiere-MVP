package tn.leaderscodes.planetecroisiere.remote.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class mediaResponse implements Serializable {

    @SerializedName("image")
    private String pictureName;

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
}
