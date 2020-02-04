package com.example.brijeshkum.mybaseproject

import com.example.brijeshkum.mybaseproject.db.model.Country

object Utils {
    @JvmStatic
    fun toArray(list: List<Country?>): Array<Country?> {
        val countries = arrayOfNulls<Country>(list.size)
        for (i in list.indices) {
            countries[i] = list[i]
        }
        return countries
    }
}