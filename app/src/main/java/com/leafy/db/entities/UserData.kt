package com.leafy.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="userdata")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "token")
    val token: String,
    @ColumnInfo(name = "userid")
    val userId: String,
    @ColumnInfo(name = "username")
    val username: String
)