package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 *
 */
public class Cruise implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("ship")
    private Boat boat;

    @SerializedName("cruiseLine")
    private CruiseLine cruiseLine;

    @SerializedName("company")
    private CruiseLine company;

    @SerializedName("stopOver")
    private Place place;

    @SerializedName("geo")
    private SubRegion geo;

    @SerializedName("itinerary")
    private Itinerary itinerary;

    @SerializedName("cruiseLength")
    private Integer cruiseLength;

    @SerializedName("detail_price_not_include")
    private String detail_price_not_include;

    @SerializedName("detail_price_include")
    private String detail_price_include;

    @SerializedName("information")
    private String information;

    @SerializedName("description")
    private String description;

    @SerializedName("good_to_know")
    private String good_to_know;

    @SerializedName("detail")
    private String detail;

    @SerializedName("condition")
    private String condition;
    @SerializedName("formality")
    private String formality;

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public CruiseLine getCruiseLine() {
        return cruiseLine;
    }

    public void setCruiseLine(CruiseLine cruiseLine) {
        this.cruiseLine = cruiseLine;
    }

    public CruiseLine getCompany() {
        return company;
    }

    public void setCompany(CruiseLine company) {
        this.company = company;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public Integer getCruiseLength() {
        return cruiseLength;
    }

    public void setCruiseLength(Integer cruiseLength) {
        this.cruiseLength = cruiseLength;
    }

    public String getDetail_price_not_include() {
        return detail_price_not_include;
    }

    public void setDetail_price_not_include(String detail_price_not_include) {
        this.detail_price_not_include = detail_price_not_include;
    }

    public String getDetail_price_include() {
        return detail_price_include;
    }

    public void setDetail_price_include(String detail_price_include) {
        this.detail_price_include = detail_price_include;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGood_to_know() {
        return good_to_know;
    }

    public void setGood_to_know(String good_to_know) {
        this.good_to_know = good_to_know;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public SubRegion getGeo() {
        return geo;
    }

    public void setGeo(SubRegion geo) {
        this.geo = geo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormality() {
        return formality;
    }

    public void setFormality(String formality) {
        this.formality = formality;
    }

    @Override
    public String toString() {
        return "Cruise{" +
                "name='" + name + '\'' +
                ", boat=" + boat +
                ", cruiseLine=" + cruiseLine +
                ", company=" + company +
                ", place=" + place +
                ", geo=" + geo +
                ", itinerary=" + itinerary +
                ", cruiseLength=" + cruiseLength +
                ", detail_price_not_include='" + detail_price_not_include + '\'' +
                ", detail_price_include='" + detail_price_include + '\'' +
                ", information='" + information + '\'' +
                ", description='" + description + '\'' +
                ", good_to_know='" + good_to_know + '\'' +
                ", detail='" + detail + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }

}