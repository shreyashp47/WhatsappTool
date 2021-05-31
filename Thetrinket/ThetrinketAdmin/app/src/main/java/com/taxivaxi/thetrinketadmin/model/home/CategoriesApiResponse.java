package com.taxivaxi.thetrinketadmin.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class CategoriesApiResponse {

    @SerializedName("categories_id")
    @Expose
    private String categoriesId;
    @SerializedName("categories_name")
    @Expose
    private String categoriesName;
    @SerializedName("categories_slug")
    @Expose
    private String categoriesSlug;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("imgpath")
    @Expose
    private String imgpath;
    @SerializedName("iconpath")
    @Expose
    private String iconpath;
    @SerializedName("categories_status")
    @Expose
    private String categoriesStatus;
    @SerializedName("parent_name")
    @Expose
    private String parentName;
    @SerializedName("childs")
    @Expose
    private List<Child> childs = null;

    public String getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(String categoriesId) {
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
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

    public String getCategoriesStatus() {
        return categoriesStatus;
    }

    public void setCategoriesStatus(String categoriesStatus) {
        this.categoriesStatus = categoriesStatus;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<Child> getChilds() {
        return childs;
    }

    public void setChilds(List<Child> childs) {
        this.childs = childs;
    }
}
