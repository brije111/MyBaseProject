package com.example.brijeshkum.mybaseproject;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Inject;

public final class NetManager {
    private Context context;

    public NetManager(Context context) {
        this.context = context;
    }

    public boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
