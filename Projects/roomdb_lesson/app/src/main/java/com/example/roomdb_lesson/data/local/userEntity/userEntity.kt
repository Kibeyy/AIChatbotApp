package com.example.roomdb_lesson.data.local.userEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
class userEntity (
    val firstName:String = "",
    val isChecked:Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val userId:Int = 0
)
