package com.example.exam_2_b18dccn100.model;

import java.io.Serializable;

public class Items implements Serializable {

    private String name;
    private int id;

    public Items() {
    }

    public Items(String name,int id) {
        this.name = name;
        this.id = id;
    }

    public Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
