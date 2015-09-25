package com.example.gerardogtn.banorteapp.data.local;

import com.example.gerardogtn.banorteapp.R;

public class ServiceList
{
    public static int NUM_IDS = 10;
    public static int ID_PAGAR_LUZ=0;
    public static int ID_PAGAR_GAS=1;
    public static int ID_PAGAR_TELEFONO=2;
    public static int ID_PAGAR_CABLE=3;
    public static int ID_PAGAR_PREDIAL=4;
    public static int ID_PAGAR_AGUA=5;
    public static int ID_PAGAR_COLEGIATURA=6;
    public static int ID_CONSULTAR_CUENTAS=7;
    public static int ID_TRANSACCIONES=8;
    public static int ID_TIEMPO_AIRE=9;

    public static String[] DESCRIPCIONES = {"Pago de servicio de luz","Pago de tanque de gas","Pago del servicio de telefonia","Pago del servicio de cable","Pago del impuesto predial","Pago del servicio de agua","Pago de colegiatura", "Visualización de estado de cuenta", "Traslado de dinero", "Deposito de tiempo aire"};

    public static String[] TITULOS = {"Pagar luz","Pagar gas","Pagar telefono","Pagar cable","Pagar predial","Pagar agua","Pagar colegiatura","Consultar cuentas","Transacciones","Tiempo aire"};

    public static String[] CLAVE ={"CFE", "Tanques de gas", "Telmex", "Izzi","Gobierno","Impuesto anual", "Tec. de Monterrey", "Jose", "Abono", "Movistar"};

    public static int[] IMAGENES = {R.mipmap.img_servicio_luz, R.mipmap.img_servicio_gas, R.mipmap.img_servicio_telefonia, R.mipmap.img_servicio_cable, R.mipmap.img_servicio_predial,R.mipmap.img_servicio_agua, R.mipmap.img_servicio_colegiatura, R.mipmap.img_servicio_cuentas, R.mipmap.img_servicio_transferencia, R.mipmap.img_servicio_tiempo};

};
