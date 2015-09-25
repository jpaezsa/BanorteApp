package com.example.gerardogtn.banorteapp.service.PreferencesHelper;

import android.content.Context;

public class PreferencesUtil
{



    public static void setIntSharedPreference(Context context, String title, String attr, int value)
    {
        context.getSharedPreferences(title, Context.MODE_PRIVATE)
                .edit().putInt(attr, value).apply();
    }

    public static int getIntSharedPreference(Context context, String title, String attr, int defValue)
    {
        return context.getSharedPreferences(title, Context.MODE_PRIVATE)
                .getInt(attr, defValue);
    }



    public static int getIntCount(Context context, int id)
    {
        return getIntSharedPreference(context,"APPLICATION_COUNT",id+"",0);
    }

    public static void setPlusCountPreference(Context context, int id)
    {
        int actualCount = getIntSharedPreference(context,"APPLICATION_COUNT",id+"",0);
        setIntSharedPreference(context,"APPLICATION_COUNT",id+"",actualCount+1);
    }

    public static void setCountPreference(Context context, int id, int count)
    {
        setIntSharedPreference(context,"APPLICATION_COUNT",id+"",count);
    }

}
