package com.leafy.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leafy.db.entities.UserData

@Dao
interface UserDataDao {
    @Query("select * from userdata")
    fun getAll(): LiveData<List<UserData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookmarkLocation: UserData): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(bookmarkLocation: UserData)

    @Query("DELETE FROM userdata")
    fun deleteAll()
}