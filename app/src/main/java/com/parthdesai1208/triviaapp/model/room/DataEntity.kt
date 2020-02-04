package com.parthdesai1208.triviaapp.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date : String,
    val name : String,
    val cricketer : String,
    val flagColor : String
) {
}