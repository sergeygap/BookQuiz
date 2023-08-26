package com.gap.bookquiz.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class Game(
    @PrimaryKey(autoGenerate = true)
    val rightBookId: Int = 0,
    val bookText: String,
    val bookCover: Int,
    val selectedBookId: Int?
)