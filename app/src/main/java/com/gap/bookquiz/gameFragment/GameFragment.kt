package com.gap.bookquiz.gameFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gap.bookquiz.R
import com.gap.bookquiz.database.AppDatabase
import com.gap.bookquiz.database.GameDao
import com.gap.bookquiz.databinding.FragmentGameBinding
import com.gap.bookquiz.databinding.FragmentStartBinding
import com.gap.bookquiz.startFragment.StartViewModel
import com.gap.bookquiz.startFragment.StartViewModelFactory

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getInstance(application).gameDao
        val viewModelFactory = GameViewModelFactory(dao)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(GameViewModel::class.java)
        viewModel.gameLiveData.observe(requireActivity()) { game ->
            binding.title.text = game.bookText
            binding.imageView2.setImageResource(game.bookCover)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}