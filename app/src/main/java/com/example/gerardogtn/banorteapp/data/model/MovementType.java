package com.example.gerardogtn.banorteapp.data.model;

/**
 * Created by gerardogtn on 9/23/15.
 */
public class MovementType {
    private int movementTypeId;
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
