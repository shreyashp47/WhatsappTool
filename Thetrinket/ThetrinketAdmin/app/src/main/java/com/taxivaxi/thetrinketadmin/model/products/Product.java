package com.taxivaxi.thetrinketadmin.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("products_id")
    @Expose
    private Integer productsId;
    @SerializedName("products_quantity")
    @Expose
    private Integer productsQuantity;
    @SerializedName("products_model")
    @Expose
    private String productsModel;
    @SerializedName("products_image")
    @Expose
    private String productsImage;
    @SerializedName("products_video_link")
    @Expose
    private Object productsVideoLink;
    @SerializedName("products_price")
    @Expose
    private String productsPrice;
    @SerializedName("products_date_added")
    @Expose
    private String productsDateAdded;
    @SerializedName("products_last_modified")
    @Expose
    private Object productsLastModified;
    @SerializedName("products_date_available")
    @Expose
    private Object productsDateAvailable;
    @SerializedName("products_weight")
    @Expose
    private String productsWeight;
    @SerializedName("products_weight_unit")
    @Expose
    private Object productsWeightUnit;
    @SerializedName("products_status")
    @Expose
    private Integer productsStatus;
    @SerializedName("is_current")
    @Expose
    private Integer isCurrent;
    @SerializedName("products_tax_class_id")
    @Expose
    private Integer productsTaxClassId;
    @SerializedName("manufacturers_id")
    @Expose
    private Object manufacturersId;
    @SerializedName("products_ordered")
    @Expose
    private Integer productsOrdered;
    @SerializedName("products_liked")
    @Expose
    private Integer productsLiked;
    @SerializedName("low_limit")
    @Expose
    private Integer lowLimit;
    @SerializedName("is_feature")
    @Expose
    private Integer isFeature;
    @SerializedName("products_slug")
    @Expose
    private String productsSlug;
    @SerializedName("products_type")
    @Expose
    private Integer productsType;
    @SerializedName("products_min_order")
    @Expose
    private Integer productsMinOrder;
    @SerializedName("products_max_stock")
    @Expose
    private Integer productsMaxStock;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("language_id")
    @Expose
    private Integer languageId;
    @SerializedName("products_name")
    @Expose
    private String productsName;
    @SerializedName("products_description")
    @Expose
    private String productsDescription;
    @SerializedName("products_url")
    @Expose
    private Object productsUrl;
    @SerializedName("products_viewed")
    @Expose
    private Integer productsViewed;
    @SerializedName("products_left_banner")
    @Expose
    private Object productsLeftBanner;
    @SerializedName("products_left_banner_start_date")
    @Expose
    private Integer productsLeftBannerStartDate;
    @SerializedName("products_left_banner_expire_date")
    @Expose
    private Integer productsLeftBannerExpireDate;
    @SerializedName("products_right_banner")
    @Expose
    private Object productsRightBanner;
    @SerializedName("products_right_banner_start_date")
    @Expose
    private Integer productsRightBannerStartDate;
    @SerializedName("products_right_banner_expire_date")
    @Expose
    private Integer productsRightBannerExpireDate;
    @SerializedName("specials_id")
    @Expose
    private Object specialsId;
    @SerializedName("manufacturer_name")
    @Expose
    private Object manufacturerName;
    @SerializedName("manufacturers_slug")
    @Expose
    private Object manufacturersSlug;
    @SerializedName("date_added")
    @Expose
    private Object dateAdded;
    @SerializedName("last_modified")
    @Expose
    private Object lastModified;
    @SerializedName("manufacturer_image")
    @Expose
    private Object manufacturerImage;
    @SerializedName("special_products_id")
    @Expose
    private Object specialProductsId;
    @SerializedName("specials_products_price")
    @Expose
    private Object specialsProductsPrice;
    @SerializedName("specials_date_added")
    @Expose
    private Object specialsDateAdded;
    @SerializedName("specials_last_modified")
    @Expose
    private Object specialsLastModified;
    @SerializedName("expires_date")
    @Expose
    private Object expiresDate;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("productupdate")
    @Expose
    private String productupdate;
    @SerializedName("categories_id")
    @Expose
    private Integer categoriesId;
    @SerializedName("categories_name")
    @Expose
    private String categoriesName;

    public Integer getProductsId() {
        return productsId;
    }

    public Integer getProductsQuantity() {
        return productsQuantity;
    }

    public String getProductsModel() {
        return productsModel;
    }

    public String getProductsImage() {
        return productsImage;
    }

    public Object getProductsVideoLink() {
        return productsVideoLink;
    }

    public String getProductsPrice() {
        return productsPrice;
    }

    public String getProductsDateAdded() {
        return productsDateAdded;
    }

    public Object getProductsLastModified() {
        return productsLastModified;
    }

    public Object getProductsDateAvailable() {
        return productsDateAvailable;
    }

    public String getProductsWeight() {
        return productsWeight;
    }

    public Object getProductsWeightUnit() {
        return productsWeightUnit;
    }

    public Integer getProductsStatus() {
        return productsStatus;
    }

    public Integer getIsCurrent() {
        return isCurrent;
    }

    public Integer getProductsTaxClassId() {
        return productsTaxClassId;
    }

    public Object getManufacturersId() {
        return manufacturersId;
    }

    public Integer getProductsOrdered() {
        return productsOrdered;
    }

    public Integer getProductsLiked() {
        return productsLiked;
    }

    public Integer getLowLimit() {
        return lowLimit;
    }

    public Integer getIsFeature() {
        return isFeature;
    }

    public String getProductsSlug() {
        return productsSlug;
    }

    public Integer getProductsType() {
        return productsType;
    }

    public Integer getProductsMinOrder() {
        return productsMinOrder;
    }

    public Integer getProductsMaxStock() {
        return productsMaxStock;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public String getProductsName() {
        return productsName;
    }

    public String getProductsDescription() {
        return productsDescription;
    }

    public Object getProductsUrl() {
        return productsUrl;
    }

    public Integer getProductsViewed() {
        return productsViewed;
    }

    public Object getProductsLeftBanner() {
        return productsLeftBanner;
    }

    public Integer getProductsLeftBannerStartDate() {
        return productsLeftBannerStartDate;
    }

    public Integer getProductsLeftBannerExpireDate() {
        return productsLeftBannerExpireDate;
    }

    public Object getProductsRightBanner() {
        return productsRightBanner;
    }

    public Integer getProductsRightBannerStartDate() {
        return productsRightBannerStartDate;
    }

    public Integer getProductsRightBannerExpireDate() {
        return productsRightBannerExpireDate;
    }

    public Object getSpecialsId() {
        return specialsId;
    }

    public Object getManufacturerName() {
        return manufacturerName;
    }

    public Object getManufacturersSlug() {
        return manufacturersSlug;
    }

    public Object getDateAdded() {
        return dateAdded;
    }

    public Object getLastModified() {
        return lastModified;
    }

    public Object getManufacturerImage() {
        return manufacturerImage;
    }

    public Object getSpecialProductsId() {
        return specialProductsId;
    }

    public Object getSpecialsProductsPrice() {
        return specialsProductsPrice;
    }

    public Object getSpecialsDateAdded() {
        return specialsDateAdded;
    }

    public Object getSpecialsLastModified() {
        return specialsLastModified;
    }

    public Object getExpiresDate() {
        return expiresDate;
    }

    public String getPath() {
        return path;
    }

    public String getProductupdate() {
        return productupdate;
    }

    public Integer getCategoriesId() {
        return categoriesId;
    }

    public String getCategoriesName() {
        return categoriesName;
    }
}
