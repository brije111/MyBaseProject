package com.example.brijeshkum.mybaseproject.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "contact")
class Contact (@PrimaryKey @SerializedName("_id") val _id: String,
               @ColumnInfo(name = "firstName") @SerializedName("firstName") val firstName: String,
               @ColumnInfo(name = "lastName") @SerializedName("lastName") val lastName: String,
               @ColumnInfo(name = "email") @SerializedName("email") val email: String,
               @ColumnInfo(name = "company") @SerializedName("company") val company: String,
               @ColumnInfo(name = "phone") @SerializedName("phone") val phone: String)