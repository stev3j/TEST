package com.example.roomtest.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomtest.model.User

//Database와 통신
//[기능]
@Dao
interface UserDao {

    //추가
    //onConflict : 중복된 Primary Key(id) 값이 존재할 경우 무시(IGNORE)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    //수정
    @Update
    suspend fun updateUser(user: User)

    //삭제
    @Delete
    suspend fun deleteUser(user: User)

    // <-- 세부적인 기능을 만들기 위해서는 Query 어노테이션을 작성함 -->
    // <-- + SQL 문을 작성함 -->

    //모든 유저 삭제하기
    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    //추가한 데이터 읽기 기능
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE id LIKE :searchQuery OR firstName LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<User>>
}