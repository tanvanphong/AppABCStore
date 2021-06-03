package model;

import java.io.Serializable;

public class Giohang implements Serializable {
    public String productId;
    public String productImage;
    public String productName;
    public int price;
    public int productNumber;

    public Giohang(String productId, String productImage, String productName, int price, int productNumber) {
        this.productId = productId;
        this.productImage = productImage;
        this.productName = productName;
        this.price = price;
        this.productNumber = productNumber;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }
}