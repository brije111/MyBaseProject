package com.example.brijeshkum.mybaseproject.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.brijeshkum.mybaseproject.db.model.Country;

import java.util.List;

import io.reactivex.Single;

/**
 * Data Access Object for the users table.
 */
@Dao
public interface CountryDao {

    /**
     * Get the user from the table. Since for simplicity we only have one user in the database,
     * this query gets all users from the table, but limits the result to just the 1st user.
     *
     * @return the user from the table
     */
    @Query("SELECT * FROM country")
    Single<List<Country>> getAll();

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param countries the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Country... countries);

    /**
     * Delete all users.
     */
    @Query("DELETE FROM country")
    void deleteAll();
}

