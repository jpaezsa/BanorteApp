package com.example.gerardogtn.banorteapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by gerardogtn on 9/23/15.
 */
public class UserProductResponse {

    @SerializedName("CLIENTEID")
    private int clientId;

    @SerializedName("ESTATUS")
    private String status;

    @SerializedName("NOCUENTA")
    private int accountId;

    @SerializedName("DESCRIPCION")
    private String description;

    @SerializedName("NOMBREPRODUCTO")
    private String productName;

    @SerializedName("TIPOPRODUCTO")
    private String productType;

    @SerializedName("SALDO")
    private BigDecimal balance;

    @Override
    public String toString() {
        return "UserProductResponse{" +
                "clientId=" + clientId +
                ", status='" + status + '\'' +
                ", accountId=" + accountId +
                ", description='" + description + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", balance=" + balance +
                '}';
    }

    public UserProductResponse(int clientId, String status, int accountId, String description,
                               String productName, String productType, BigDecimal balance) {
        this.clientId = clientId;
        this.status = status;
        this.accountId = accountId;
        this.description = description;
        this.productName = productName;
        this.productType = productType;
        this.balance = balance;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
