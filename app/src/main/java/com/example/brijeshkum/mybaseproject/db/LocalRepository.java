package com.example.brijeshkum.mybaseproject.db;

import android.os.AsyncTask;

import com.example.brijeshkum.mybaseproject.db.model.Country;

import java.util.List;

import io.reactivex.Single;

public class LocalRepository {

   private AppDatabase appDatabase;

   public LocalRepository(AppDatabase appDatabase){
       this.appDatabase = appDatabase;
   }

   Single<List<Country>> getCountries(){
        return appDatabase.countryDao().getAll();
    }

   void saveCountryLocal(final List<Country> list) {
       AsyncTask.execute(new Runnable() {
           @Override
           public void run() {
               Country[] countries = new Country[list.size()];
               for (int i = 0; i < list.size(); i++) {
                   countries[i] = list.get(i);
               }
               appDatabase.countryDao().insertAll(countries);
           }
       });
   }
}
