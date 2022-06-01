package br.org.lsitec.android.quizz.webclient

import br.org.lsitec.android.quizz.model.Question
import br.org.lsitec.android.quizz.webclient.services.QuizService
import retrofit2.Response

class QuizWebClient {

    private val quizService: QuizService = RetrofitInstance().quizService

    suspend fun getRandomQuestions(): Response<List<Question>> {
        return quizService.getRandomQuestions()
    }
}