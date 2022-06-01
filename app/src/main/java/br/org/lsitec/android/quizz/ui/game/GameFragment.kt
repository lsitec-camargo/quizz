package br.org.lsitec.android.quizz.ui.game

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.org.lsitec.android.quizz.R
import br.org.lsitec.android.quizz.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel
    private var correctAnswerIndex: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGameBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.eventGameFinish.observe(viewLifecycleOwner) { isFinished ->
            if (isFinished) {
                gameFinished()
                viewModel.onGameFinished()
            }
        }

        viewModel.eventShowCorrectAnswer.observe(viewLifecycleOwner) { show ->
            if (show)
                setCorrectAnswerColor()
            else
                resetCorrectAnswerColor()
        }

        return binding.root
    }

    private fun gameFinished() {
        val currentScore = viewModel.score.value ?: 0
        val action = GameFragmentDirections.actionGameFragmentToEndgameFragment(currentScore)
        findNavController().navigate(action)
    }

    private fun setCorrectAnswerColor() {
        correctAnswerIndex = viewModel.correctAnswerIndex
        val green = ContextCompat.getColor(requireContext(), R.color.colorPrimaryVariant)
        correctAnswerIndex?.let {
            binding.gameAnswerGroup.getChildAt(it)
                .setBackgroundColor(green)
        }
    }

    private fun resetCorrectAnswerColor() {
        correctAnswerIndex?.let {
            binding.gameAnswerGroup.getChildAt(it)
                .setBackgroundColor(Color.TRANSPARENT)
        }
    }
}