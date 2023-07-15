package com.example.editprofile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserInfo::class], version = 2 )

abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserInfoDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null


        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                return instance
            }
        }
    }
}