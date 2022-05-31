package br.org.lsitec.android.quizz.ui.endgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EndgameViewModel(score: Int, numQuestions: Int) : ViewModel() {

    private val _finalScore = MutableLiveData<Int>()
    val finalScore: LiveData<Int> get() = _finalScore

    private val _percentage = MutableLiveData<Int>()
    val percentage: LiveData<Int> get() = _percentage

    private val _nQuestions = MutableLiveData<Int>()
    val nQuestions: LiveData<Int> get() = _nQuestions

    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean> get() = _eventPlayAgain

    init {
        _finalScore.value = score
        _nQuestions.value = numQuestions
        _percentage.value = (score * 100) / numQuestions
        _eventPlayAgain.value = false
    }

    fun onPlayAgain() {
        _eventPlayAgain.value = true
    }

    fun onPlayAgainComplete() {
        _eventPlayAgain.value = false
    }

}