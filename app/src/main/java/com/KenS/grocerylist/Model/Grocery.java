package com.KenS.grocerylist.Model;

public class Grocery {
    private String name;
    private String quantity;
    private String dateItemAdded;
    private String id;

    public Grocery() {
    }

    public Grocery(String name, String quantity, String dateItemAdded, String id) {
        this.name = name;
        this.quantity = quantity;
        this.dateItemAdded = dateItemAdded;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDateItemAdded() {
        return dateItemAdded;
    }

    public void setDateItemAdded(String dateItemAdded) {
        this.dateItemAdded = dateItemAdded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
