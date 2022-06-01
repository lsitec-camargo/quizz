package br.org.lsitec.android.quizz.ui.endgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class EndgameViewModelFactory(
    private val score: Int,
    private val numQuestions: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EndgameViewModel::class.java)) {
            return EndgameViewModel(score, numQuestions) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }

}