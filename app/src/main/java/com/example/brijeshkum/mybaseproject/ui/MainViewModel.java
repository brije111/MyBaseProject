package com.example.brijeshkum.mybaseproject.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.util.Log;
import com.example.brijeshkum.mybaseproject.db.Repository;
import com.example.brijeshkum.mybaseproject.db.model.Country;

import java.util.List;

import javax.inject.Inject;

//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.observers.DisposableSingleObserver;
//import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private static final String TAG = "RegistrationViewModel";
    private final Repository repository;
    private LiveData<List<Country>> listCountry;

    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

  public void init() {
    if (this.listCountry != null) {
      // ViewModel is created on a per-Fragment basis, so the userId
      // doesn't change.
      return;
    }
    listCountry = repository.getCountries();
  }


  public LiveData<List<Country>> getListCountry() {
        return listCountry;
    }

  public ObservableBoolean isLoading() {
    return repository.getIsLoading();
  }

    /*private void loadCountries(){
        isLoading.set(true);
        repository.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Country>>() {
                    @Override
                    public void onSuccess(List<Country> list) {
                        listCountry.postValue(list);
                        repository.saveCountryLocal(list);
                        isLoading.set(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        isLoading.set(false);
                        Log.e(TAG, "onError: ", e);
                    }
                });
    }*/
}
