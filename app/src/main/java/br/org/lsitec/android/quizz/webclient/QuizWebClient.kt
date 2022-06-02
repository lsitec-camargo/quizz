package br.org.lsitec.android.quizz.webclient

import android.util.Log
import br.org.lsitec.android.quizz.model.Question
import br.org.lsitec.android.quizz.model.QuizMetadata
import br.org.lsitec.android.quizz.webclient.services.QuizService
import retrofit2.Response
import java.util.*

class QuizWebClient {

    private val quizService: QuizService = RetrofitInstance().quizService

    suspend fun getQuestions(category: String): Response<List<Question>> {
        if(category == "Random")
            return quizService.getRandomQuestions()
        val filterCategory = category.replace(" ", "_")
            .lowercase(Locale.getDefault())
        return quizService.getQuestions(filterCategory)
    }

    suspend fun getCategories(): Response<QuizMetadata> {
        val response = quizService.getCategories()
        Log.i("Client", "${response.code()} response: ${response.body()}")
        return response
    }
}