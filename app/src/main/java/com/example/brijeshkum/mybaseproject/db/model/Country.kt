package com.example.brijeshkum.mybaseproject.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country")
class Country /*@Ignore
    public State(String code, String name) {
        mId = UUID.randomUUID().toString();
        mCode = code;
        mName = name;
    }*/(@field:ColumnInfo(name = "commonmasterid") @field:PrimaryKey @field:Expose @field:SerializedName("commonmasterid") val id: Int,
        @field:ColumnInfo(name = "code") @field:Expose @field:SerializedName("code") val code: String,
        @field:ColumnInfo(name = "name") @field:Expose @field:SerializedName("name") val name: String)