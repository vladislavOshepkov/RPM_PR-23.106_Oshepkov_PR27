package com.example.sqlite

import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(nameEntity: NameEntity)

    @Delete
    suspend fun deleteItem(nameEntity: NameEntity)

    @Query("SELECT * FROM NameEntity")
    fun getAllItems(): Flow<List<NameEntity>>
}