package com.leafy.db

import android.content.Context
import androidx.room.Database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.leafy.db.daos.UserDataDao
import com.leafy.db.entities.UserData

@Database(
    entities = [UserData::class],
    version = 3,
    exportSchema = false
)

abstract class LeafyDB : RoomDatabase() {

    abstract fun userDataDao(): UserDataDao

    companion object {
        @Volatile
        private var INSTANCE: LeafyDB? = null

        fun getInstance(context: Context): LeafyDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LeafyDB::class.java,
                        "leafy_db"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}
