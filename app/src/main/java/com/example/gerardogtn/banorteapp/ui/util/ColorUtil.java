package com.example.gerardogtn.banorteapp.ui.util;

public class ColorUtil {

    public static int hexToDec(String hex)
    {
        String digits = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        int val = 0;
        for (int i = 0; i < hex.length(); ++i) {
            char c = hex.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

    public static String decToHex(int dec, int size)
    {
        String s = "";
        for (int n = dec; n > 0; n /= 16) {
            int r = n % 16;
            s = r < 10 ? r + s : (char) ('A' - 10 + r) + s;
        }

        if (s.length() > size)
        {
            s = "";
            for (int i = 0; i < size; i++) s+= "F";
        }else{
            int actLen = s.length();
            for (int i = 0; i < size-actLen; i++)
                s = "0" + s;
        }

        return s;
    }

    public static String changeColorBrightness(String color, int bright)
    {
        return
            (decToHex((int)(hexToDec(color.substring(1,3)) * (bright / 100f)),2) +
             decToHex((int)(hexToDec(color.substring(3,5)) * (bright / 100f)),2) +
             decToHex((int)(hexToDec(color.substring(5,7)) * (bright / 100f)),2));
    }

}
