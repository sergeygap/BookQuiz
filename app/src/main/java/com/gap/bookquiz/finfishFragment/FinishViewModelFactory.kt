package com.gap.bookquiz.finfishFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gap.bookquiz.database.GameDao

class FinishViewModelFactory(private val dao: GameDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FinishViewModel::class.java)) {
            return FinishViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}