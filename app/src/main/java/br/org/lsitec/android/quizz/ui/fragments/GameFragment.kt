package br.org.lsitec.android.quizz.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.org.lsitec.android.quizz.R
import br.org.lsitec.android.quizz.databinding.FragmentGameBinding
import br.org.lsitec.android.quizz.model.Question
import br.org.lsitec.android.quizz.webclient.QuizWebClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding get() = _binding!!

    private val webClient by lazy {
        QuizWebClient()
    }

    private val navController by lazy {
        findNavController()
    }

    private var numberOfQuestions: Int = 0
    private var questions: MutableList<Question>? = null
    private lateinit var currentQuestion: Question
    private lateinit var answers: MutableList<String>
    private var correctAnswerIndex: Int = -1
    private var score: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            questions = webClient.getRandomQuestions()?.toMutableList()
            numberOfQuestions = questions!!.size
            setQuiz()
        }

        configureSubmitButton()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setQuiz() {
        initializeQuestion()
        with(binding) {
            gameQuestion.text = currentQuestion.question
            gameFirstAnswer.text = answers[0]
            gameSecondAnswer.text = answers[1]
            gameThirdAnswer.text = answers[2]
            gameFourthAnswer.text = answers[3]
        }
    }

    private fun initializeQuestion() {
        questions?.let {
            currentQuestion = it.removeFirst()
            answers = currentQuestion.incorrectAnswers.toMutableList()
            answers.add(currentQuestion.correctAnswer)
            answers.shuffle()
        }
    }

    private fun configureSubmitButton() {
        binding.gameSubmitButton.setOnClickListener {
            val checkedAnswer = binding.gameAnswerGroup.checkedRadioButtonId
            if (checkedAnswer >= 0) {
                val userAnswerIndex = getAnswerIndex(checkedAnswer)

                checkUserAnswer(userAnswerIndex)

                questions?.let {
                    if (it.isEmpty()) {
                        navController.navigate(GameFragmentDirections
                            .actionGameFragmentToEndgameFragment(score, numberOfQuestions))
                    } else {
                        reloadQuiz()
                    }
                }
            }
        }
    }

    private fun getAnswerIndex(checkedAnswer: Int): Int {
        var answerIndex = 0
        when (checkedAnswer) {
            R.id.game_second_answer -> answerIndex = 1
            R.id.game_third_answer -> answerIndex = 2
            R.id.game_fourth_answer -> answerIndex = 3
        }
        correctAnswerIndex = answers.indexOf(currentQuestion.correctAnswer)
        return answerIndex
    }

    private fun checkUserAnswer(userAnswerIndex: Int) {
        if (userAnswerIndex == correctAnswerIndex) {
            score++
        }
        val green = ContextCompat.getColor(requireContext(), R.color.colorPrimaryVariant)
        binding.gameAnswerGroup.getChildAt(correctAnswerIndex)
            .setBackgroundColor(green)
    }

    private fun reloadQuiz(
    ) = lifecycleScope.launch {
        delay(1000)
        setQuiz()
        binding.gameAnswerGroup.getChildAt(correctAnswerIndex)
            .setBackgroundColor(Color.TRANSPARENT)
    }
}