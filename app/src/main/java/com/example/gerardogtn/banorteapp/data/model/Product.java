package com.example.gerardogtn.banorteapp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gerardogtn on 9/23/15.
 */
public class Product {

    @SerializedName("PRODUCTOID")
    private int id;

    @SerializedName("TIPOPRODUCTO")
    private String type;

    @SerializedName("NOMBREPRODUCTO")
    private String name;

    @SerializedName("DESCRIPCION")
    private String description;

    public Product(int id, String type, String name, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
