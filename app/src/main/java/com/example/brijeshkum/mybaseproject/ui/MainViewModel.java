package com.example.brijeshkum.mybaseproject.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import com.example.brijeshkum.mybaseproject.db.Repository;
import com.example.brijeshkum.mybaseproject.db.model.Country;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final Repository repository;
    private LiveData<List<Country>> listCountry;
    private MutableLiveData<Integer> loading;

    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<Integer> getLoading() {
        if (this.loading == null){
            loading = repository.getLoading();
        }
        return loading;
    }

    public LiveData<List<Country>> getListCountry() {
        if (this.listCountry == null) {
            listCountry = repository.getCountries();
        }
        return listCountry;
    }

}
