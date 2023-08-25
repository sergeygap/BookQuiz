package com.gap.bookquiz.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameDAO {

    @Insert
    fun insert(book: Game)

    @Query("UPDATE game SET selectedBookId = NULL") // мб ошибка
    fun updateAll(book: Game)

    @Query("UPDATE game SET selectedBookId = :selectedBookId WHERE rightBookId = :rightBookId")
    fun updateSelectId(rightBookId: Int, selectedBookId: Int)

    @Query("SELECT rightBookId, bookText, bookCover FROM game WHERE rightBookId = :rightBookId")
    fun getQuestion(rightBookId: Int): Game

    @Query("SELECT * FROM game WHERE selectedBookId IS NOT NULL")
    fun getAll(): List<Game>

    @Query("SELECT COUNT(rightBookId) FROM game WHERE rightBookId = selectedBookId AND selectedBookId IS NOT NULL")
    fun countRightAnswers(): Int

//    @Update
//    fun updateSelectId(rightBookId: Int, selectedBookId: Int)

}