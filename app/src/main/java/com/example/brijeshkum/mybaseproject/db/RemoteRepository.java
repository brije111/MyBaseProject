package com.example.brijeshkum.mybaseproject.db;

import android.content.SharedPreferences;

import com.example.brijeshkum.mybaseproject.db.model.Country;

import java.util.List;

import io.reactivex.Single;

public class RemoteRepository {

    private static final String TAG = "RemoteRepository";
    private ApiEndpointInterface apiEndpointInterface;
    private SharedPreferences preferences;

    public RemoteRepository(ApiEndpointInterface apiEndpointInterface, SharedPreferences preferences){
        this.apiEndpointInterface = apiEndpointInterface;
        this.preferences = preferences;
    }

    Single<List<Country>> getCountries(){
        return apiEndpointInterface.getCountries(4);
    }
}
