package com.gap.bookquiz.gameFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gap.bookquiz.database.GameDao

class GameViewModelFactory(private val dao: GameDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}