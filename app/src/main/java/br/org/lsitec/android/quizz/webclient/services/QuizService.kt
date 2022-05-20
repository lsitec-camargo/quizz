package br.org.lsitec.android.quizz.webclient.services

import br.org.lsitec.android.quizz.model.Question
import retrofit2.Response
import retrofit2.http.GET

interface QuizService {

    @GET("questions")
    suspend fun getRandomQuestions(): Response<List<Question>>

}