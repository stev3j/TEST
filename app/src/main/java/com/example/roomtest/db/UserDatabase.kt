package com.example.roomtest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomtest.model.User

//DAO 정보를 가져옴
@Database(entities = [User::class], version = 1, exportSchema = false)
//abstract class : 추상 클래스 : 미완성된 클래스(상속 받아 구체화가 되야함)
abstract class UserDatabase : RoomDatabase() {

    //추상 클래스의 함수(확정되지 않음)
    abstract fun userDao(): UserDao

    //상속받아지는 클래스에서 모두 동일한 값이 들어감
    companion object{
        @Volatile
        //인스턴스 : 할당된 메모리(구체화된 객체)
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}