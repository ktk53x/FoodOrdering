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
}
