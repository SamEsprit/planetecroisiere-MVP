package tn.leaderscodes.planetecroisiere.remote.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import tn.leaderscodes.planetecroisiere.remote.entities.User;

public class UserRequest  implements Serializable {

    @SerializedName("user")
    private User user;

    public UserRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
