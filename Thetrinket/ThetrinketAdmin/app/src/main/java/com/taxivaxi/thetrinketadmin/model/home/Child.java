package com.taxivaxi.thetrinketadmin.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Child {

    @SerializedName("categories_id")
    @Expose
    private Integer categoriesId;
    @SerializedName("categories_name")
    @Expose
    private String categoriesName;
    @SerializedName("categories_slug")
    @Expose
    private String categoriesSlug;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("imgpath")
    @Expose
    private String imgpath;
    @SerializedName("iconpath")
    @Expose
    private String iconpath;
    @SerializedName("categories_status")
    @Expose
    private Integer categoriesStatus;
    @SerializedName("parent_name")
    @Expose
    private String parentName;


    public Integer getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Integer categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getCategoriesSlug() {
        return categoriesSlug;
    }

    public void setCategoriesSlug(String categoriesSlug) {
        this.categoriesSlug = categoriesSlug;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    public Integer getCategoriesStatus() {
        return categoriesStatus;
    }

    public void setCategoriesStatus(Integer categoriesStatus) {
        this.categoriesStatus = categoriesStatus;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
