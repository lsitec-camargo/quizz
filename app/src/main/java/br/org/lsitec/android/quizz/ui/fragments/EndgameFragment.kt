package br.org.lsitec.android.quizz.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.org.lsitec.android.quizz.R
import br.org.lsitec.android.quizz.databinding.FragmentEndgameBinding
import br.org.lsitec.android.quizz.databinding.FragmentGameBinding

private const val TAG = "Endgame"

class EndgameFragment : Fragment() {

    private var _binding: FragmentEndgameBinding? = null
    private val binding: FragmentEndgameBinding get() = _binding!!

    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEndgameBinding.inflate(inflater, container, false)

        val score = arguments?.getInt("score") ?: 0
        val numberOfQuestions = arguments?.getInt("numberOfQuestions") ?: 1
        val percentage = (score * 100) / numberOfQuestions

        with(binding) {
            endgameProgress.progress = percentage
            endgamePercentageScore.text = "$percentage%"
            endgameScore.text = "You got $score out of $numberOfQuestions"
            endgamePlayAgain.setOnClickListener {
                navController.navigate(EndgameFragmentDirections.actionEndgameFragmentToGameFragment())
            }
        }

        return binding.root
    }

}