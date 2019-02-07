package tn.leaderscodes.planetecroisiere.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 *
 */
public class CruiseLine implements Serializable {
    static final long serialVersionUID = 1;

    /**
     *
     */
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
    @SerializedName("logo")
    private String image;

    /**
     *
     */
    @SerializedName("long_text")
    private String long_text;

    /**
     *
     */
    @SerializedName("short_text")
    private String short_text;

    /**
     *
     */
    @SerializedName("brochure_text")
    private String brochure_text;

    /**
     *
     */
    @SerializedName("good_to_know")
    private String good_to_know;

    /**
     *
     */
    @SerializedName("detail_price_not_include")
    private String detail_price_not_include;

    /**
     *
     */
    @SerializedName("detail_price_include")
    private String detail_price_include;


    /**
     *
     */
    @SerializedName("createdAt")
    private String createdAt;

    /**
     *
     */
    @SerializedName("updatedAt")
    private String updatedAt;


    public CruiseLine(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

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

    public String getLong_text() {
        return long_text;
    }

    public void setLong_text(String long_text) {
        this.long_text = long_text;
    }

    public String getShort_text() {
        return short_text;
    }

    public void setShort_text(String short_text) {
        this.short_text = short_text;
    }

    public String getBrochure_text() {
        return brochure_text;
    }

    public void setBrochure_text(String brochure_text) {
        this.brochure_text = brochure_text;
    }

    public String getGood_to_know() {
        return good_to_know;
    }

    public void setGood_to_know(String good_to_know) {
        this.good_to_know = good_to_know;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}