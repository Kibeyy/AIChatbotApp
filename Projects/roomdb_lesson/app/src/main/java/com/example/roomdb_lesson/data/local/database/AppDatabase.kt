package com.example.roomdb_lesson.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb_lesson.data.local.userDao.userDao
import com.example.roomdb_lesson.data.local.userEntity.userEntity

@Database(
    entities = [userEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase:RoomDatabase() {
    abstract val userDao:userDao

}