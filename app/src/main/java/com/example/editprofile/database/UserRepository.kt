package com.example.editprofile.database

class UserRepository(private val userInfoDao: UserDao) {

    suspend fun insertInfo(info: UserInfo) {
        userInfoDao.insertUserInfo(info)
    }
    fun getUser(): UserInfo?{
       // val user = UserInfo(username = "name", password = "ogb")
        return  userInfoDao.getUserInfo()

    }
}
