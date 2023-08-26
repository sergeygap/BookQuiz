package com.gap.bookquiz.startFragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gap.bookquiz.R
import com.gap.bookquiz.database.Game
import com.gap.bookquiz.database.GameDao
import kotlinx.coroutines.launch


class StartViewModel(val dao: GameDao) : ViewModel() {

    fun addQuestions(context: Context) {
        viewModelScope.launch {

            val isEmpty = dao.isDatabaseEmpty() == 0

            if (isEmpty) {

                val gameElement0 = Game(
                    bookText = context.getString(R.string.anna_karenina),
                    bookCover = R.drawable.anna_karenina, // imageResourceId
                    selectedBookId = null
                )
                dao.insert(gameElement0)

                val gameElement1 = Game(
                    bookText = context.getString(R.string.pride_and_prejudice),
                    bookCover = R.drawable.pride_and_prejudice, // imageResourceId
                    selectedBookId = null
                )
                dao.insert(gameElement1)

                val gameElement2 = Game(
                    bookText = context.getString(R.string.lord_of_the_flies),
                    bookCover = R.drawable.lord_of_the_flies, // imageResourceId
                    selectedBookId = null
                )
                dao.insert(gameElement2)

                val gameElement3 = Game(
                    bookText = context.getString(R.string.ten_little_negroes),
                    bookCover = R.drawable.ten_little_negroes, // imageResourceId
                    selectedBookId = null
                )
                dao.insert(gameElement3)

                val gameElement4 = Game(
                    bookText = context.getString(R.string.crime_and_punishment),
                    bookCover = R.drawable.crime_and_punishment, // imageResourceId
                    selectedBookId = null
                )
                dao.insert(gameElement4)

                val gameElement5 = Game(
                    bookText = context.getString(R.string.the_picture_of_dorian_gray),
                    bookCover = R.drawable.the_picture_of_dorian_gray, // imageResourceId
                    selectedBookId = null
                )
                dao.insert(gameElement5)

                val gameElement6 = Game(
                    bookText = context.getString(R.string.the_little_prince),
                    bookCover = R.drawable.the_little_prince, // imageResourceId
                    selectedBookId = null
                )
                dao.insert(gameElement6)

                val gameElement7 = Game(
                    bookText = context.getString(R.string.two_leagues_under_the_sea),
                    bookCover = R.drawable.two_leagues_under_the_sea, // imageResourceId
                    selectedBookId = null
                )
                dao.insert(gameElement7)

                val gameElement8 = Game(
                    bookText = context.getString(R.string.the_old_man_and_the_sea),
                    bookCover = R.drawable.the_old_man_and_the_sea, // imageResourceId
                    selectedBookId = null
                )
                dao.insert(gameElement8)

                val gameElement9 = Game(
                    bookText = context.getString(R.string.the_master_and_margarita),
                    bookCover = R.drawable.the_master_and_margarita, // imageResourceId
                    selectedBookId = null
                )
                dao.insert(gameElement9)

                val gameElement10 = Game(
                    bookText = context.getString(R.string.the_adventures_of_sherlock_holmes),
                    bookCover = R.drawable.the_adventures_of_sherlock_holmes, // imageResourceId
                    selectedBookId = null
                )
                dao.insert(gameElement10)

                val gameElement11 = Game(
                    bookText = context.getString(R.string.the_great_gatsby),
                    bookCover = R.drawable.the_great_gatsby, // imageResourceId
                    selectedBookId = null
                )
                dao.insert(gameElement11)
            }
        }
    }
    fun updateTable() {
        viewModelScope.launch {
            dao.updateAllQuestion()
        }
    }
}