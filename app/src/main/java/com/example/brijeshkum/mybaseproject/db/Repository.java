package com.example.brijeshkum.mybaseproject.db;

import com.example.brijeshkum.mybaseproject.NetManager;
import com.example.brijeshkum.mybaseproject.db.model.Country;

import java.util.List;

import io.reactivex.Single;

public class Repository {
    private NetManager netManager;
    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    public Repository(NetManager netManager, LocalRepository localRepository, RemoteRepository remoteRepository){
        this.netManager = netManager;
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public Single<List<Country>> getCountries(){
        if (netManager.isConnected())
            return remoteRepository.getCountries();
        else return localRepository.getCountries();
    }

    /*
        methods saving data in local
     */
    public void saveCountryLocal(List<Country> list){
        localRepository.saveCountryLocal(list);
    }
}
