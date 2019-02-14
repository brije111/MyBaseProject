package com.example.brijeshkum.mybaseproject.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.util.Log;
import com.example.brijeshkum.mybaseproject.db.Repository;
import com.example.brijeshkum.mybaseproject.db.model.Country;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private static final String TAG = "RegistrationViewModel";
    private Repository repository;
    private MutableLiveData<List<Country>> listCountry;

    private ObservableBoolean isLoading = new ObservableBoolean();

    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }


    public MutableLiveData<List<Country>> getListCountry() {
        if (listCountry==null){
            listCountry = new MutableLiveData<>();
            loadCountries();
        }
        return listCountry;
    }

    private void loadCountries(){
        isLoading.set(true);
        repository.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Country>>() {
                    @Override
                    public void onSuccess(List<Country> list) {
                        listCountry.postValue(list);
                        repository.saveCountryLocal(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        isLoading.set(false);
                        Log.e(TAG, "onError: ", e);
                    }
                });
    }
}
