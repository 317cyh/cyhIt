package com.fh.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartBean implements Serializable {

        private String id;
        private BigDecimal price;
        private String productImg;
        private String subtitle;
        private String productName;

        private Integer productCount;
        private BigDecimal productAllPrice;
        private Boolean productSelect;

        private Integer productAllCount;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getProductAllCount() {
        return productAllCount;
    }

    public void setProductAllCount(Integer productAllCount) {
        this.productAllCount = productAllCount;
    }

    public BigDecimal getProductAllPrice() {
        return productAllPrice;
    }

    public void setProductAllPrice(BigDecimal productAllPrice) {
        this.productAllPrice = productAllPrice;
    }

    public Boolean getProductSelect() {
        return productSelect;
    }

    public void setProductSelect(Boolean productSelect) {
        this.productSelect = productSelect;
    }
}
