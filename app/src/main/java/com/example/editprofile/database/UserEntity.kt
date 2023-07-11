package com.example.editprofile.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "UserTable")

data class UserInfo (val username :String, val password :String) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}