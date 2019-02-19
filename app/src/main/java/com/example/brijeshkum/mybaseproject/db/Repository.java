package com.example.brijeshkum.mybaseproject.db;

import android.arch.lifecycle.LiveData;
import android.databinding.ObservableBoolean;

import com.example.brijeshkum.mybaseproject.NetManager;
import com.example.brijeshkum.mybaseproject.db.model.Country;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class Repository {
  private static final int FRESH_TIMEOUT = 10;
  private final Executor executor;
    private final AppDatabase mAppDatabase;
    private final ApiEndpointInterface mApiEndpointInterface;

  private ObservableBoolean isLoading = new ObservableBoolean();
  public ObservableBoolean getIsLoading() {
    return isLoading;
  }

    @Inject
  public Repository(Executor executor, AppDatabase appDatabase, ApiEndpointInterface apiEndpointInterface) {
    this.executor = executor;
    mAppDatabase = appDatabase;
    mApiEndpointInterface = apiEndpointInterface;
  }

  public LiveData<List<Country>> getCountries() {
    refreshCountry();
    // Returns a LiveData object directly from the database.
    return mAppDatabase.countryDao().getAll();
  }

  private void refreshCountry() {
    // Runs in a background thread.
    executor.execute(() -> {
      // Check if user data was fetched recently.
      //boolean userExists = localRepository.getAppDatabase().countryDao().hasCountries
      //    (FRESH_TIMEOUT);
      //if (!userExists) {
        // Refreshes the data.
      Response<List<Country>> response = null;
      try {
        response = mApiEndpointInterface.getCountries(4).execute();
      } catch (IOException e) {
        e.printStackTrace();
      }

      // Check for errors here.

        // Updates the database. The LiveData object automatically
        // refreshes, so we don't need to do anything else here.
        Country[] countries = new Country[response.body().size()];
        for (int i = 0; i < response.body().size(); i++) {
          countries[i] = response.body().get(i);
        }
        mAppDatabase.countryDao().insertAll(countries);
      //}
    });
  }
}
