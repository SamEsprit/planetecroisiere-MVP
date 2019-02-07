package tn.leaderscodes.planetecroisiere.remote.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;

public class SubRegionResponse implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("subRegions")
    private List<SubRegion> subRegionList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubRegion> getSubRegionList() {
        return subRegionList;
    }

    public void setSubRegionList(List<SubRegion> subRegionList) {
        this.subRegionList = subRegionList;
    }
}
