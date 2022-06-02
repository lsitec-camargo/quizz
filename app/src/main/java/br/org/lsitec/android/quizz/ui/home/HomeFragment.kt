package br.org.lsitec.android.quizz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.org.lsitec.android.quizz.R
import br.org.lsitec.android.quizz.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.categories.observe(viewLifecycleOwner) {
            setSpinner(it)
        }

        binding.homeNewGameButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGameFragment(
                category = binding.homeCategorySpinner.selectedItem.toString()
            ))
        }

        return binding.root
    }

    private fun setSpinner(categories: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.homeCategorySpinner.adapter = adapter
    }

}