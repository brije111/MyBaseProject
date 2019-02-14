package com.example.brijeshkum.mybaseproject.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "country")
public class Country {

    @SerializedName("commonmasterid")
    @Expose
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "commonmasterid")
    private int mId;

    @SerializedName("code")
    @Expose
    @ColumnInfo(name = "code")
    private String mCode;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String mName;

    /*@Ignore
    public State(String code, String name) {
        mId = UUID.randomUUID().toString();
        mCode = code;
        mName = name;
    }*/

    public Country(int id, String code, String name) {
        this.mId = id;
        this.mCode = code;
        this.mName = name;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getCode() {
        return mCode;
    }
}

