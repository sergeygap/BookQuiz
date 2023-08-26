package com.gap.bookquiz.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {

    @Insert
    fun insert(book: Game)


    @Query("UPDATE game SET selectedBookId = NULL") // мб ошибка
    fun updateAllQuestion()

    @Query("UPDATE game SET selectedBookId = :selectedBookId WHERE rightBookId = :rightBookId")
    fun updateSelectId(rightBookId: Int, selectedBookId: Int)

    @Query("SELECT * FROM game WHERE rightBookId =:rightBookId")
    fun getOneQuestion(rightBookId: Int): Game

    @Query("SELECT * FROM game WHERE selectedBookId IS NOT NULL")
    fun getAll(): List<Game>

    @Query("SELECT COUNT(rightBookId) FROM game WHERE rightBookId = selectedBookId AND selectedBookId IS NOT NULL")
    fun countRightAnswers(): Int


}