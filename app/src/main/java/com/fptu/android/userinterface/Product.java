package com.fptu.android.userinterface;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private String id, name, price, surl;

    public Product() {
    }

    public Product(String id, String name, String price, String surl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.surl = surl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("name",name);
        return result;
    }
}
