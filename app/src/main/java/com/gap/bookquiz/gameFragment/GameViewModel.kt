package com.gap.bookquiz.gameFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gap.bookquiz.database.Game
import com.gap.bookquiz.database.GameDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(val dao: GameDao) : ViewModel() {
    private val numbers = (1..12).toList()
    private val shuffledNumbers = numbers.shuffled()
    private var i = 0

    private val _gameLiveData = MutableLiveData<Game>()
    val gameLiveData: LiveData<Game> = _gameLiveData
    init {
        getNewQuestion()
    }
    fun getNewQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            val game = dao.getOneQuestion(shuffledNumbers[i++])
            _gameLiveData.postValue(game)
        }
    }
}