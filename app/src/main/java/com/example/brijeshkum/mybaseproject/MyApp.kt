package com.example.brijeshkum.mybaseproject

import android.app.Application
import com.example.brijeshkum.mybaseproject.di.AppComponent
import com.example.brijeshkum.mybaseproject.di.AppModule
import com.example.brijeshkum.mybaseproject.di.DaggerAppComponent
import com.example.brijeshkum.mybaseproject.di.NetModule

class MyApp : Application() {
    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        // Dagger%COMPONENT_NAME%
        appComponent = DaggerAppComponent.builder() // list of modules that are part of this component need to be created here too
                .appModule(AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(NetModule("https://express-rest-app.herokuapp.com/"))
                .build()
        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
// then we can use .create() as a shortcut instead:
//  mAppComponent = com.codepath.dagger.components.DaggerAppComponent.create();
    }
}