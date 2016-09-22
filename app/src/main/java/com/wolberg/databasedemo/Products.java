package com.wolberg.databasedemo;

/**
 * Created by 660251521 on 9/21/2016.
 */
public class Products {
    private int _id; //underscore indicates PRIMARY KEY
    private String productName;

    public Products(){

    }

    public Products(String productName){
        this.productName = productName;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
