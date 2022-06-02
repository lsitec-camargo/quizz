package br.org.lsitec.android.quizz.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.org.lsitec.android.quizz.R
import br.org.lsitec.android.quizz.model.Question
import br.org.lsitec.android.quizz.webclient.QuizWebClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel(private val category: String) : ViewModel() {

    private var nQuestion: Int = 0
    private lateinit var quiz: List<Question>
    var correctAnswerIndex: Int? = null
    var radioChecked = MutableLiveData<Int>()

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question> get() = _question

    private val _answers = MutableLiveData<List<String>>()
    val answers: LiveData<List<String>> get() = _answers

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean> get() = _eventGameFinish

    private val _eventShowCorrectAnswer = MutableLiveData<Boolean>()
    val eventShowCorrectAnswer: LiveData<Boolean> get() = _eventShowCorrectAnswer

    private val _status = MutableLiveData<GameStatus>()
    val status: LiveData<GameStatus> = _status

    init {
        _eventGameFinish.value = false
        _score.value = 0
        getQuizQuestions()
    }

    private fun getQuizQuestions() {
        viewModelScope.launch {
            _status.value = GameStatus.LOADING
            try {
                val response = QuizWebClient().getQuestions(category)
                response.body()?.let {
                    quiz = it
                    setQuestion()
                    _status.value = GameStatus.DONE
                }
            } catch (e: Exception) {
                _status.value = GameStatus.ERROR
            }
        }
    }

    private fun setQuestion() {
        _question.value = quiz[nQuestion]
        _answers.value = question.value?.shuffleAnswers()
        correctAnswerIndex = answers.value?.indexOf(question.value?.correctAnswer)
        nQuestion++
    }

    fun onSubmit() {
        _eventShowCorrectAnswer.value = true
        viewModelScope.launch {
            delay(500)
            if (nQuestion >= quiz.size) {
                _eventGameFinish.value = true
            } else if (radioChecked.value != null) {
                checkUserAnswer(radioChecked.value!!)
                setQuestion()
            }
            _eventShowCorrectAnswer.value = false
        }
    }

    private fun checkUserAnswer(checkedRadioButtonIndex: Int) {
        var answerIndex = 0
        when (checkedRadioButtonIndex) {
            R.id.game_second_answer -> answerIndex = 1
            R.id.game_third_answer -> answerIndex = 2
            R.id.game_fourth_answer -> answerIndex = 3
        }

        if (answerIndex == correctAnswerIndex) {
            _score.value = _score.value?.plus(1)
        }
    }

    fun onGameFinished() {
        _eventGameFinish.value = false
    }

}

enum class GameStatus {
    LOADING,
    DONE,
    ERROR
}