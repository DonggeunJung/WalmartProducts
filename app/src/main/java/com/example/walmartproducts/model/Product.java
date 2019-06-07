package com.example.walmartproducts.model;

/*
 * Product.java : Product data class
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Jun.06.2019
 */
public class Product {
    protected String productId;
    protected String productName;
    protected String shortDescription;
    protected String longDescription;
    protected String price;
    protected String productImage;
    protected float reviewRating;
    protected int reviewCount;
    protected boolean inStock;

    public Product(String productId, String productName, String shortDescription,
                   String longDescription, String price, String productImage,
                   float reviewRating, int reviewCount, boolean inStock) {
        this.productId = productId;
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
        this.productImage = productImage;
        this.reviewRating = reviewRating;
        this.reviewCount = reviewCount;
        this.inStock = inStock;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getPrice() {
        return price;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public float getReviewRating() {
        return reviewRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public boolean isInStock() {
        return inStock;
    }

}
