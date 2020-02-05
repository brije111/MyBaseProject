package com.example.brijeshkum.mybaseproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brijeshkum.mybaseproject.db.Repository
import com.example.brijeshkum.mybaseproject.db.RepositoryInterface
import com.example.brijeshkum.mybaseproject.db.model.Contact
import javax.inject.Inject

class MainViewModel @Inject constructor(repository: RepositoryInterface) : ViewModel() {
    var listContact: LiveData<List<Contact?>?>? = repository.getContacts()
    var loading: MutableLiveData<Int>? = repository.loading()
}