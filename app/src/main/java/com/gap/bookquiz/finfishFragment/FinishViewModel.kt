package com.gap.bookquiz.finfishFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gap.bookquiz.database.Game
import com.gap.bookquiz.database.GameDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FinishViewModel(val dao: GameDao) : ViewModel() {

    private val _numberLiveData = MutableLiveData<Int>()
    val numberLiveData: LiveData<Int> = _numberLiveData

    private val _answerLiveData = MutableLiveData<List<Game>>()
    val answerLiveData: LiveData<List<Game>> = _answerLiveData
    fun updateTable() {
        viewModelScope.launch {
            dao.updateAllQuestion()
        }
    }

    fun getRightNumberAnswers() {
        viewModelScope.launch(Dispatchers.IO) {
            val number = dao.countRightAnswers()
            _numberLiveData.postValue(number)
        }
    }

    fun getAllIsNotNull() {
        viewModelScope.launch {
            val answers = dao.getAllIsNotNull()
            _answerLiveData.postValue(answers)
        }
    }


}