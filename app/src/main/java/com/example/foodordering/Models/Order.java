package com.example.foodordering.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Order {
    private UUID rID;
    private UUID uID;
    private LocalDateTime dateTime;
    private Float cost;
    private Boolean cash;
    private ArrayList<UUID> items;
    private ArrayList<Integer> quantity;

    public Order() {
    }

    public Order(UUID rID, UUID uID, LocalDateTime dateTime, Float cost, Boolean cash, ArrayList<UUID> items, ArrayList<Integer> quantity) {
        this.rID = rID;
        this.uID = uID;
        this.dateTime = dateTime;
        this.cost = cost;
        this.cash = cash;
        this.items = items;
        this.quantity = quantity;
    }

    public UUID getrID() {
        return rID;
    }

    public void setrID(UUID rID) {
        this.rID = rID;
    }

    public UUID getuID() {
        return uID;
    }

    public void setuID(UUID uID) {
        this.uID = uID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Boolean getCash() {
        return cash;
    }

    public void setCash(Boolean cash) {
        this.cash = cash;
    }

    public ArrayList<UUID> getItems() {
        return items;
    }

    public void setItems(ArrayList<UUID> items) {
        this.items = items;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(ArrayList<Integer> quantity) {
        this.quantity = quantity;
    }
}
