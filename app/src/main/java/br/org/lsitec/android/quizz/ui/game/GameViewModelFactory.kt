package br.org.lsitec.android.quizz.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class GameViewModelFactory(
    private val category: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(category) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }

}