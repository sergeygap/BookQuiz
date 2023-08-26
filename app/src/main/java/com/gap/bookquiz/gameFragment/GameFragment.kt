package com.gap.bookquiz.gameFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gap.bookquiz.finfishFragment.FinishFragment
import com.gap.bookquiz.R
import com.gap.bookquiz.database.AppDatabase
import com.gap.bookquiz.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GameViewModel
    private var rightBookId: Int = -1
    private var countGame: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
        viewModel.getAllQuestion()
        viewModel.gameLiveData.observe(requireActivity()) { game ->
            rightBookId = game.rightBookId
            game.selectedBookId
            binding.title.text = game.bookText
            viewModel.allLiveData.observe(requireActivity()) {
                val img = viewModel.selectImage(game.bookCover, it)
                setImage(img.shuffled())
            }
        }
        setOnClickListeners()

    }

    private fun setOnClickListeners() {


        binding.imageView1.setOnClickListener { view ->
            val clickedResId = view?.tag as Int
            viewModel.updateSelectedId(rightBookId, clickedResId)
            endGame()
        }

        binding.imageView2.setOnClickListener { view ->
            val clickedResId = view?.tag as Int
            viewModel.updateSelectedId(rightBookId, clickedResId)
            endGame()
        }

        binding.imageView3.setOnClickListener { view ->
            val clickedResId = view?.tag as Int
            viewModel.updateSelectedId(rightBookId, clickedResId)
            endGame()
        }


    }

    private fun endGame() {
        countGame++
        Log.d("TAG12", "setOnClickListeners: $countGame")
        if (countGame < 10) {
            viewModel.getNewQuestion()
        } else {
            launchFragment(FinishFragment())
        }
    }

    private fun launchFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.main_fragment_container_view,
                fragment
            )
            .commit()
    }

    private fun setImage(selectImage: List<Int>) {
        binding.imageView1.setImageResource(selectImage[0])
        binding.imageView1.tag = selectImage[0]
        binding.imageView2.setImageResource(selectImage[1])
        binding.imageView2.tag = selectImage[1]
        binding.imageView3.setImageResource(selectImage[2])
        binding.imageView3.tag = selectImage[2]
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}