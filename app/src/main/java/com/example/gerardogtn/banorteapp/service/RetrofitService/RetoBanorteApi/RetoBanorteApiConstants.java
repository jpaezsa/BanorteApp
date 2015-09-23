package com.example.gerardogtn.banorteapp.service.RetrofitService.RetoBanorteApi;

import java.net.URL;

/**
 * Created by gerardogtn on 9/23/15.
 */
public class RetoBanorteApiConstants {

    public static final String URL_BASE = "http://serviciosretobanorte.mybluemix.net";

    public static final String PARAM_CLIENTS = "/consultaCliente";
    public static final String PARAM_CLIENT_PRODUCTS = "/consultaProductoCliente";
    public static final String PARAM_ACCOUNT_BALANCE = "/consultaSaldo";
    public static final String PARAM_PRODUCT_TYPE = "/consultaProducto";
    public static final String PARAM_ACCOUNT_MOVEMENTS = "/consultaMovimientos";
    public static final String PARAM_MOVEMENT_TYPE = "/consultaCatalogoMovimientos";
    public static final String PARAM_TRANSFER = "/transferencia";


    public static final String URL_USERS = URL_BASE + PARAM_CLIENTS;
    public static final String URL_USER_PRODUCTS = URL_BASE + PARAM_CLIENT_PRODUCTS;
    public static final String URL_ACCOUNT_BALANCE = URL_BASE + PARAM_ACCOUNT_BALANCE;
    public static final String URL_PRODUCT_TYPE = URL_BASE + PARAM_PRODUCT_TYPE;
    public static final String URL_ACCOUNT_MOVEMENTS = URL_BASE + PARAM_ACCOUNT_MOVEMENTS;
    public static final String URL_MOVEMENT_TYPES = URL_BASE + PARAM_MOVEMENT_TYPE;
    public static final String URL_TRANSFER = URL_BASE + PARAM_TRANSFER;

}