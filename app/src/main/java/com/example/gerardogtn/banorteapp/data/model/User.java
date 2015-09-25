package com.example.gerardogtn.banorteapp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gerardogtn on 9/23/15.
 */
public class User {

    @SerializedName("CLIENTEID")
    private int id;

    @SerializedName("NOMBRE")
    private String firstName;

    @SerializedName("APELLIDOPATERNO")
    private String paternalLastName;

    @SerializedName("APELLIDOMATERNO")
    private String maternalLastName;

    public User(int id, String firstName, String paternalLastName, String maternalLastName) {
        this.id = id;
        this.firstName = firstName;
        this.paternalLastName = paternalLastName;
        this.maternalLastName = maternalLastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPaternalLastName() {
        return paternalLastName;
    }

    public void setPaternalLastName(String paternalLastName) {
        this.paternalLastName = paternalLastName;
    }

    public String getMaternalLastName() {
        return maternalLastName;
    }

    public void setMaternalLastName(String maternalLastName) {
        this.maternalLastName = maternalLastName;
    }
}

