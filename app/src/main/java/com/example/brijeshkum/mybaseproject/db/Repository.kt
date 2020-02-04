package com.example.brijeshkum.mybaseproject.db

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.brijeshkum.mybaseproject.Utils.toArray
import com.example.brijeshkum.mybaseproject.db.model.Country
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executor
import javax.inject.Inject

//import android.databinding.ObservableBoolean;
class Repository @Inject constructor(private val executor: Executor, private val mMyRoomDatabase: MyRoomDatabase, private val mWebServices: WebServices) {
    val loading = MutableLiveData<Int>()

    // Returns a LiveData object directly from the database.
    val countries: LiveData<List<Country?>?>?
        get() {
            refreshCountry()
            // Returns a LiveData object directly from the database.
            return mMyRoomDatabase.countryDao().all
        }

    private fun refreshCountry() { // Runs in a background thread.
        executor.execute {
            // Check if user data was fetched recently.
//boolean userExists = localRepository.getAppDatabase().countryDao().hasCountries
//    (FRESH_TIMEOUT);
//if (!userExists) {
// Refreshes the data.
//for showing progress on UI
            loading.postValue(View.VISIBLE)
            var response: Response<List<Country?>?>? = null
            try {
                response = mWebServices.getCountries(4).execute()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            //for hiding progress on UI
            loading.postValue(View.GONE)
            // Check for errors here.
            if (response != null && response.isSuccessful) // Updates the database. The LiveData object automatically
// refreshes, so we don't need to do anything else here.
                mMyRoomDatabase.countryDao().insertAll(*toArray(response.body()!!))
        }
    }

    companion object {
        private const val FRESH_TIMEOUT = 10
    }

    init {
        loading.value = View.GONE
    }
}