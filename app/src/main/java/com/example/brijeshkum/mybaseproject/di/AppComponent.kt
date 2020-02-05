package com.example.brijeshkum.mybaseproject.di

//import com.example.brijeshkum.mybaseproject.HomeActivity
import com.example.brijeshkum.mybaseproject.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {
    //fun inject(activity: HomeActivity) // void inject(MyFragment fragment);
    fun inject(activity: MainActivity)
}