package com.example.gerardogtn.banorteapp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gerardogtn on 9/23/15.
 */
public class MovementType {

    @SerializedName("TIPOMOVIMIENTOID")
    private int movementTypeId;

    @SerializedName("DESCRIPCION")
    private String description;

    public MovementType(int movementTypeId, String description) {
        this.movementTypeId = movementTypeId;
        this.description = description;
    }

    public int getMovementTypeId() {
        return movementTypeId;
    }

    public void setMovementTypeId(int movementTypeId) {
        this.movementTypeId = movementTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
