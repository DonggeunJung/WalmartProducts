package com.example.walmartproducts.model;

import java.util.List;

public class Mart {
    public List<Product> products;
    protected int totalProducts;
    protected int pageNumber;
    protected int pageSize;
    protected int statusCode;

    public Mart(List<Product> products, int totalProducts, int pageNumber,
                int pageSize, int statusCode) {
        this.products = products;
        this.totalProducts = totalProducts;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.statusCode = statusCode;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
