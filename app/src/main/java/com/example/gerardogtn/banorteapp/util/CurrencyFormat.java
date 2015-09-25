package com.example.gerardogtn.banorteapp.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by gerardogtn on 9/24/15.
 */
public class CurrencyFormat {

    // REQUIRES: None.
    // MODIFIES: None.
    // EFFECTS: If amount < 0 returns "$0.00" else. return a currency representation of amount.
    public static String getCurrencyFormat(BigDecimal amount){
        if (amount.intValue() < 0){
            return "$0.00";
        }
        else{
            return NumberFormat.getCurrencyInstance().format(amount);
        }
    }
}
