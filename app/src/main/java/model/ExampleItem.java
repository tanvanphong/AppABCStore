package model;

import java.io.LineNumberInputStream;
import java.io.Serializable;

public class ExampleItem implements Serializable {

    public String ID;
    public String ProductName;
    public int Price;
    public String Image;
    public String ProductDetail;


    public ExampleItem(String ID, String productName, int price, String image, String productDetail) {
        this.ID = ID;
        ProductName = productName;
        Price = price;
        Image = image;
        ProductDetail = productDetail;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getProductDetail() {
        return ProductDetail;
    }

    public void setProductDetail(String productDetail) {
        ProductDetail = productDetail;
    }
}