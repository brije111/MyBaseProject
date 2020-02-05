package com.example.brijeshkum.mybaseproject.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.brijeshkum.mybaseproject.db.model.Contact

/**
 * Data Access Object for the users table.
 */
@Dao
interface ContactDao {
    /**
     * Get the user from the table. Since for simplicity we only have one user in the database,
     * this query gets all users from the table, but limits the result to just the 1st user.
     *
     * @return the user from the table
     */
    @get:Query("SELECT * FROM contact")
    val all: LiveData<List<Contact?>?>?

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param contacts the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg contacts: Contact?)

    /**
     * Delete all users.
     */
    @Query("DELETE FROM contact")
    fun deleteAll()
}