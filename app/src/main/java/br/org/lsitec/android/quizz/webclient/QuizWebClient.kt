package br.org.lsitec.android.quizz.webclient

import android.util.Log
import br.org.lsitec.android.quizz.model.Question
import br.org.lsitec.android.quizz.webclient.services.QuizService
import java.lang.Exception

private const val TAG = "QuizWebClient"

class QuizWebClient {

    private val quizService: QuizService = RetrofitInstance().quizService

    suspend fun getRandomQuestions(): List<Question>? {
        val apiResponse = quizService.getRandomQuestions()
        return try {
            Log.i(TAG, "response code: ${apiResponse.code()}")
            apiResponse.body()
        } catch (e: Exception) {
            Log.i(TAG, "response code: ${apiResponse.code()}, error: $e")
            null
        }
    }
}