package com.gap.bookquiz.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {

    @Insert
    suspend fun insert(book: Game)

    @Query("UPDATE game SET selectedBookId = NULL")
    suspend fun updateAllQuestion()

    @Query("UPDATE game SET selectedBookId = :selectedBookId WHERE rightBookId = :rightBookId")
    suspend fun updateSelectId(rightBookId: Int, selectedBookId: Int)

    @Query("SELECT * FROM game WHERE rightBookId =:rightBookId AND selectedBookId IS NULL")
    suspend fun getOneQuestion(rightBookId: Int): Game

    @Query("SELECT * FROM game WHERE rightBookId =:rightBookId")
    suspend fun getAnotherCover(rightBookId: Int): Game

    @Query("SELECT * FROM game")
    suspend fun getAll(): List<Game>

    @Query("SELECT * FROM game WHERE selectedBookId IS NOT NULL")
    suspend fun getAllIsNotNull(): List<Game>

    @Query("SELECT COUNT(rightBookId) FROM game WHERE bookCover = selectedBookId AND selectedBookId IS NOT NULL")
    suspend fun countRightAnswers(): Int

    @Query("SELECT COUNT(*) FROM game")
    suspend fun isDatabaseEmpty(): Int

}