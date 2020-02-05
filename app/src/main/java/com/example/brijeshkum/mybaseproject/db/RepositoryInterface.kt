package com.example.brijeshkum.mybaseproject.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.brijeshkum.mybaseproject.db.model.Contact

interface RepositoryInterface {
    fun getContacts(): LiveData<List<Contact?>?>?
    fun loading(): MutableLiveData<Int>
}