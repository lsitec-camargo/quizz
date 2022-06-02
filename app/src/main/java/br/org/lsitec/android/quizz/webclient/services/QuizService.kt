package br.org.lsitec.android.quizz.webclient.services

import br.org.lsitec.android.quizz.model.Question
import br.org.lsitec.android.quizz.model.QuizMetadata
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizService {

    @GET("questions")
    suspend fun getRandomQuestions(): Response<List<Question>>

    @GET("questions")
    suspend fun getQuestions(@Query("categories") type: String): Response<List<Question>>

    @GET("metadata")
    suspend fun getCategories(): Response<QuizMetadata>

}