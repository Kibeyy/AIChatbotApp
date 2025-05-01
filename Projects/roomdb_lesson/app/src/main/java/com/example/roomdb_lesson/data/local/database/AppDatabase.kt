package com.example.roomdb_lesson.data.local.database

import androidx.room.Database
import com.example.roomdb_lesson.data.local.userEntity.userEntity

@Database(
    entities = [userEntity::class],
    version = 1,
    exportSchema = true
)
class Database {
}