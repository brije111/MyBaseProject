package com.example.brijeshkum.mybaseproject

import com.example.brijeshkum.mybaseproject.db.model.Contact

object Utils {
    @JvmStatic
    fun toArray(list: List<Contact?>): Array<Contact?> {
        val contacts = arrayOfNulls<Contact>(list.size)
        for (i in list.indices) {
            contacts[i] = list[i]
        }
        return contacts
    }
}