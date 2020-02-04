package com.example.brijeshkum.mybaseproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brijeshkum.mybaseproject.db.Repository
import com.example.brijeshkum.mybaseproject.db.model.Country

class MainViewModel(private val repository: Repository) : ViewModel() {
    var listCountry: LiveData<List<Country?>?>? = null
        get() {
            if (field == null) {
                field = repository.countries
            }
            return field
        }
        private set
    var loading: MutableLiveData<Int>? = null
        get() {
            if (field == null) {
                field = repository.loading
            }
            return field
        }
        private set

}