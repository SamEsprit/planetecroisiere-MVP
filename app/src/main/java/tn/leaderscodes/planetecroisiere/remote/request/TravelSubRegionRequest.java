package tn.leaderscodes.planetecroisiere.remote.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TravelSubRegionRequest implements Serializable {

    @SerializedName("code")
    private String code;
    @SerializedName("subRegionsCorps")
    private List<String> subRegionsCorps;

    public TravelSubRegionRequest(String code, List<String> subRegionsCorps) {
        this.code = code;
        this.subRegionsCorps = subRegionsCorps;
    }

    public TravelSubRegionRequest() {
        this.code = "";
        this.subRegionsCorps=new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getSubRegionsCorps() {
        return subRegionsCorps;
    }

    public void setSubRegionsCorps(List<String> subRegionsCorps) {
        this.subRegionsCorps = subRegionsCorps;
    }

    @Override
    public String toString() {
        return "TravelSubRegionRequest{" +
                "code='" + code + '\'' +
                ", subRegionsCorps=" + subRegionsCorps +
                '}';
    }
}
