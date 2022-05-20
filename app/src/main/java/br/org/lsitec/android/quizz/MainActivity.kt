package br.org.lsitec.android.quizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import br.org.lsitec.android.quizz.webclient.QuizWebClient
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val webClient by lazy {
        QuizWebClient()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val questions = webClient.getRandomQuestions()
            Log.i(TAG, "questions: $questions")
        }


    }
}