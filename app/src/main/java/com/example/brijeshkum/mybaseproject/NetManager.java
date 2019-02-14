package com.example.brijeshkum.mybaseproject;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Inject;

public abstract class NetManager {
    @Inject
    Application application;
    public static boolean get(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
