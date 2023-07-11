package com.example.editprofile.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserInfo(info: UserInfo)

    @Query("SELECT * FROM UserTable ")
    fun getUserInfo(): UserInfo

}
