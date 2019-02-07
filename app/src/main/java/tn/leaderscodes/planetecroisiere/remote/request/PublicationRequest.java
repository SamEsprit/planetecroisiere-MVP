package tn.leaderscodes.planetecroisiere.remote.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import tn.leaderscodes.planetecroisiere.remote.entities.Publication;

public class PublicationRequest implements Serializable {

    @SerializedName("feed")
    private Publication publication;

    public PublicationRequest(Publication publication) {
        this.publication = publication;
    }
}
