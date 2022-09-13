package com.example.roomtest.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

//테이블
@Parcelize //string같은 기본형이 아니라 객체로 전달해야할 때 씀
@Entity(tableName = "user_table")
data class User(
    //firstName, lastName, age를 고유하게 식별하는 값으로 id
    //>>> ex. 1 : 조 승완 17
    //    ex. 2 : 가 나다 34
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
): Parcelable

