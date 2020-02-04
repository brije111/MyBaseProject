package com.example.brijeshkum.mybaseproject.di

import com.example.brijeshkum.mybaseproject.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {
    fun inject(activity: MainActivity?) // void inject(MyFragment fragment);
// void inject(MyService service);
}