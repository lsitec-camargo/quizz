package br.org.lsitec.android.quizz.webclient

import br.org.lsitec.android.quizz.webclient.services.QuizService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://the-trivia-api.com/api/"

class RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val quizService = retrofit.create(QuizService::class.java)
}