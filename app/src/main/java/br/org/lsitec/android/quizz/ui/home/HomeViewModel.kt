package br.org.lsitec.android.quizz.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.org.lsitec.android.quizz.webclient.QuizWebClient
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> get() = _categories

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            val list = mutableListOf("Random")
            try {
                val metadata = QuizWebClient().getCategories()
                metadata.body()?.let {
                    list.addAll(it.getCategories)
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error: $e")
            }
            _categories.value = list
        }
    }

}