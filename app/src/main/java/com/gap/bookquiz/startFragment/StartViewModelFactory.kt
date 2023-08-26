package com.gap.bookquiz.startFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gap.bookquiz.database.GameDao

class StartViewModelFactory(private val dao: GameDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StartViewModel::class.java)) {
            return StartViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}