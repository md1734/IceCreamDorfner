package com.example.mitch.icecreamdorfner;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mitch on 3/20/17.
 */

public class OrderItem implements Serializable{
    Date date;
    String flavor;
    String size;
    Double cost;

    public OrderItem(Date date, String flavor, String size, Double cost) {
        this.date = date;
        this.flavor = flavor;
        this.size = size;
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "OrderItem:" + "\n" +
                "Date: " + date + "\n " +
                "Flavor: " + flavor + "\n " +
                "Size: " + size + "\n " +
                "Cost: " + cost;
    }
}
