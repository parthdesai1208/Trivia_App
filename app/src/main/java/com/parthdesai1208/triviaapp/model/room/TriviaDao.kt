package com.parthdesai1208.triviaapp.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.parthdesai1208.triviaapp.model.room.DataEntity

@Dao
interface TriviaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(model: DataEntity): Long

    @Query("Select * from DataEntity")
    fun getHistory(): List<DataEntity>
}