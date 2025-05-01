package com.example.roomdb_lesson.data.local.userDao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdb_lesson.data.local.userEntity.userEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface userDao {
    @Insert
    suspend fun insertUser(userEntity: userEntity)

    @Delete
    suspend fun deleteUser(userEntity: userEntity)

    @Query("SELECT * FROM users_table")
    fun getAllUsers():Flow<List<userEntity>>


}