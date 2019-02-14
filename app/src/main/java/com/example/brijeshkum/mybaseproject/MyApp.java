package com.example.brijeshkum.mybaseproject;

import android.app.Application;

import com.example.brijeshkum.mybaseproject.di.AppComponent;
import com.example.brijeshkum.mybaseproject.di.AppModule;
import com.example.brijeshkum.mybaseproject.di.NetModule;

public class MyApp extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        mAppComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("http://122.160.45.128:3030/api/"))
                .build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mAppComponent = com.codepath.dagger.components.DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
