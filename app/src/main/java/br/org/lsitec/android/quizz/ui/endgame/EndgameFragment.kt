package br.org.lsitec.android.quizz.ui.endgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.org.lsitec.android.quizz.databinding.FragmentEndgameBinding


class EndgameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentEndgameBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val fragmentArgs by navArgs<EndgameFragmentArgs>()
        val viewModelFactory =
            EndgameViewModelFactory(fragmentArgs.score, fragmentArgs.numQuestions)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(EndgameViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.eventPlayAgain.observe(viewLifecycleOwner) { playAgain ->
            if (playAgain) {
                findNavController().navigate(EndgameFragmentDirections.actionEndgameFragmentToGameFragment())
                viewModel.onPlayAgainComplete()
            }
        }

        return binding.root
    }

}