package tn.leaderscodes.planetecroisiere.remote.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import tn.leaderscodes.planetecroisiere.remote.entities.User;

public class UserResponse implements Serializable {

    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "user=" + user +
                '}';
    }
}
