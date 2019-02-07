package tn.leaderscodes.planetecroisiere.remote.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;

public class TravelRequest implements Serializable {

    @SerializedName("company")
    private String company;
    @SerializedName("boat")
    private String boat;
    @SerializedName("subRegions")
    private TravelSubRegionRequest subRegionRequest;
    @SerializedName("stopOver")
    private String stopOver;
    @SerializedName("dateMin")
    private String dateMin;
    @SerializedName("dateMax")
    private String dateMax;
    @SerializedName("length")
    private Integer length;

    @SerializedName("lengthPage")
    private Integer lengthPage;

    @SerializedName("start")
    private Integer start;

    public TravelRequest() {
        this.lengthPage=10;
        this.start=0;
        this.company = "";
        this.length = 0;
        this.boat = "";
        this.subRegionRequest = new TravelSubRegionRequest();
        this.stopOver = "";
        this.dateMin = "";
        this.dateMax = "";
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBoat() {
        return boat;
    }

    public void setBoat(String boat) {
        this.boat = boat;
    }

    public TravelSubRegionRequest getSubRegionRequest() {
        return subRegionRequest;
    }

    public void setSubRegionRequest(TravelSubRegionRequest subRegionRequest) {
        this.subRegionRequest = subRegionRequest;
    }

    public String getStopOver() {
        return stopOver;
    }

    public void setStopOver(String stopOver) {
        this.stopOver = stopOver;
    }

    public String getDateMin() {
        return dateMin;
    }

    public void setDateMin(String dateMin) {
        this.dateMin = dateMin;
    }

    public String getDateMax() {
        return dateMax;
    }

    public void setDateMax(String dateMax) {
        this.dateMax = dateMax;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "TravelRequest{" +
                "company='" + company + '\'' +
                ", boat='" + boat + '\'' +
                ", subRegionRequest=" + subRegionRequest +
                ", stopOver='" + stopOver + '\'' +
                ", dateMin='" + dateMin + '\'' +
                ", dateMax='" + dateMax + '\'' +
                ", length=" + length +
                '}';
    }

    public Integer getLengthPage() {
        return lengthPage;
    }

    public void setLengthPage(Integer lengthPage) {
        this.lengthPage = lengthPage;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
