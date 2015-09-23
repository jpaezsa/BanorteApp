package com.example.gerardogtn.banorteapp.data.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gerardogtn on 9/23/15.
 */
public class Movement {
    private int id;
    private Date date;
    private BigDecimal amount;
    private String description;

    public Movement(int id, Date date, BigDecimal amount, String description) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
