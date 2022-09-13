package com.example.roomtest.repository

import androidx.lifecycle.LiveData
import com.example.roomtest.db.UserDao
import com.example.roomtest.model.User

//DAO에서 기능을 호출함
//저장소
class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    //suspend : 비동기 환경에서 사용될 수 있다는 의미, 코루틴 내에서만 호출 가능
    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<User>> {
        return userDao.searchDatabase(searchQuery)
    }
}