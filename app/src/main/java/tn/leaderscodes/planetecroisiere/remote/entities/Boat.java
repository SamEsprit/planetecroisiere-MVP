package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Boat implements Serializable {
    static final long serialVersionUID = 1;

    @SerializedName("_id")
    private String _id;
    /**
     * 
     */
    @SerializedName("code")
    private String code;

    /**
     * 
     */
    @SerializedName("name")
    private String name;

    /**
     * 
     */
    @SerializedName("longText")
    private String longText;

    /**
     * 
     */
    @SerializedName("star")
    private float star;

    /**
     * 
     */
    @SerializedName("launchDate")
    private String launchDate;

    /**
     * 
     */
    @SerializedName("renewDate")
    private String renewDate;

    /**
     * 
     */
    @SerializedName("passengerLength")
    private Integer passengerLength;

    /**
     * 
     */
    @SerializedName("crewLength")
    private Integer crewLength;

    /**
     * 
     */
    @SerializedName("length")
    private Integer length;

    /**
     *
     */
    @SerializedName("cruiseLine")
    private String cruiseLine;

    /**
     * 
     */
    @SerializedName("width")
    private Integer width;

    /**
     * 
     */
    @SerializedName("speed")
    private Integer speed;

    /**
     * 
     */
    @SerializedName("tonnage")
    private Integer tonnage;

    /**
     * 
     */
    @SerializedName("voltage")
    private String voltage;

    /**
     * 
     */
    @SerializedName("pont")
    private Integer pont;

    @SerializedName("activities")
    private List<Activite> activiteList;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getRenewDate() {
        return renewDate;
    }

    public void setRenewDate(String renewDate) {
        this.renewDate = renewDate;
    }

    public Integer getPassengerLength() {
        return passengerLength;
    }

    public void setPassengerLength(Integer passengerLength) {
        this.passengerLength = passengerLength;
    }

    public Integer getCrewLength() {
        return crewLength;
    }

    public void setCrewLength(Integer crewLength) {
        this.crewLength = crewLength;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getTonnage() {
        return tonnage;
    }

    public void setTonnage(Integer tonnage) {
        this.tonnage = tonnage;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public Integer getPont() {
        return pont;
    }

    public void setPont(Integer pont) {
        this.pont = pont;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Activite> getActiviteList() {
        return activiteList;
    }

    public void setActiviteList(List<Activite> activiteList) {
        this.activiteList = activiteList;
    }

    public String getCruiseLine() {
        return cruiseLine;
    }
    public void setCruiseLine(String cruiseLine) {
        this.cruiseLine = cruiseLine;
    }

    @Override
    public String toString() {
        return  name ;
    }

    public Boat(String name) {
        this.name = name;
    }
}