package com.example.brijeshkum.mybaseproject.db

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.brijeshkum.mybaseproject.Utils.toArray
import com.example.brijeshkum.mybaseproject.db.model.Contact
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executor
import javax.inject.Inject

//import android.databinding.ObservableBoolean;
class Repository @Inject constructor(private val executor: Executor,
                                     private val mMyRoomDatabase: MyRoomDatabase,
                                     private val mWebServices: WebServices) :RepositoryInterface {
    val loading = MutableLiveData<Int>()

    private fun refreshContact() { // Runs in a background thread.
        executor.execute {
            // Check if user data was fetched recently.
//boolean userExists = localRepository.getAppDatabase().countryDao().hasCountries
//    (FRESH_TIMEOUT);
//if (!userExists) {
// Refreshes the data.
//for showing progress on UI
            loading.postValue(View.VISIBLE)
            var response: Response<List<Contact?>?>? = null
            try {
                response = mWebServices.getContacts().execute()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            //for hiding progress on UI
            loading.postValue(View.GONE)
            // Check for errors here.
            if (response != null && response.isSuccessful) // Updates the database. The LiveData object automatically
// refreshes, so we don't need to do anything else here.
                mMyRoomDatabase.contactDao().insertAll(*toArray(response.body()!!))
        }
    }

    companion object {
        private const val FRESH_TIMEOUT = 10
    }

    init {
        loading.value = View.GONE
    }

    override fun getContacts(): LiveData<List<Contact?>?>? {
        refreshContact()
        // Returns a LiveData object directly from the database.
        return mMyRoomDatabase.contactDao().all
    }

    override fun loading(): MutableLiveData<Int> {
        return loading
    }
}