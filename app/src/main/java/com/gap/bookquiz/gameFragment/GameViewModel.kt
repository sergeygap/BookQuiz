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

    private val _allLiveData = MutableLiveData<List<Game>>()
    val allLiveData: LiveData<List<Game>> = _allLiveData


    init {
        getNewQuestion()
    }

    fun getNewQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            val game = dao.getOneQuestion(shuffledNumbers[i++])
            _gameLiveData.postValue(game)
        }
    }

    fun getAllQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            val game = dao.getAll()
            _allLiveData.postValue(game)
        }
    }

    fun updateSelectedId(rightBookId: Int, selectedBookId: Int) {
        viewModelScope.launch {
            dao.updateSelectId(rightBookId, selectedBookId)
        }
    }

    fun selectImage(currentAnswerImage: Int, allGameElements: List<Game>): List<Int> {

        if (!allGameElements.isNullOrEmpty()) {
            val availableImages = allGameElements.map { it.bookCover }
            val otherImages = availableImages.filter { it != currentAnswerImage }

            val shuffledOtherImages = otherImages.shuffled()
            val selectedOtherImages = shuffledOtherImages.take(2)

            val imageList = mutableListOf<Int>()
            imageList.add(currentAnswerImage)
            imageList.addAll(selectedOtherImages)

            return imageList//.shuffled()
        } else
            return listOf(0)
    }
}