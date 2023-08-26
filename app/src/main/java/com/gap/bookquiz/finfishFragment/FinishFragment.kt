package com.gap.bookquiz.finfishFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gap.bookquiz.R
import com.gap.bookquiz.database.AppDatabase
import com.gap.bookquiz.database.Game
import com.gap.bookquiz.databinding.FragmentFinishBinding
import com.gap.bookquiz.gameFragment.GameFragment


class FinishFragment : Fragment() {

    private var _binding: FragmentFinishBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FinishViewModel
    private lateinit var listTextViewQ: List<TextView>
    private lateinit var listImageViewQ: List<ImageView>
    private lateinit var listImageViewQNotRight: List<ImageView>
    private lateinit var listTextViewQNotRight: List<TextView>
    private lateinit var listTextViewQColor: List<TextView>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getInstance(application).gameDao
        val viewModelFactory = FinishViewModelFactory(dao)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        )[FinishViewModel::class.java]
        viewModel.getRightNumberAnswers()
        viewModel.getAllIsNotNull()
        viewModel.numberLiveData.observe(requireActivity()) {
            val text = getString(R.string.number_of_correct_answer, it.toString())
            binding.title.text = text
        }
        viewModel.answerLiveData.observe(requireActivity()) {



            setAnswers(it)


        }

        setOnClickListener()

    }

    private fun addAllViewsInList() {
        listTextViewQ = listOf(
            binding.textViewQFirts,
            binding.textViewQSecond,
            binding.textViewQThird,
            binding.textViewQFour,
            binding.textViewQFive,
            binding.textViewQSix,
            binding.textViewQSeven,
            binding.textViewQEight,
            binding.textViewQNine,
            binding.textViewQTen
        )
        listImageViewQ = listOf(
            binding.imageViewQFirst,
            binding.imageViewQSecond,
            binding.imageViewQThird,
            binding.imageViewQFour,
            binding.imageViewQFive,
            binding.imageViewQSix,
            binding.imageViewQSeven,
            binding.imageViewQEight,
            binding.imageViewQNine,
            binding.imageViewQTen
        )
        listImageViewQNotRight = listOf(
            binding.imageViewQFirstNotRight,
            binding.imageViewQSecondNotRight,
            binding.imageViewQThirdNotRight,
            binding.imageViewQFourNotRight,
            binding.imageViewQFiveNotRight,
            binding.imageViewQSixNotRight,
            binding.imageViewQSevenNotRight,
            binding.imageViewQEightNotRight,
            binding.imageViewQNineNotRight,
            binding.imageViewQTenNotRight,
        )
        listTextViewQNotRight = listOf(
            binding.textViewQFirstNotRight,
            binding.textViewQSecondNotRight,
            binding.textViewQThirdNotRight,
            binding.textViewQFourNotRight,
            binding.textViewQFiveNotRight,
            binding.textViewQSixNotRight,
            binding.textViewQSevenNotRight,
            binding.textViewQEightNotRight,
            binding.textViewQNineNotRight,
            binding.textViewQTenNotRight
        )
        listTextViewQColor = listOf(
            binding.textViewQFirstColor,
            binding.textViewSecondColor,
            binding.textViewThirdColor,
            binding.textViewFourColor,
            binding.textViewFiveColor,
            binding.textViewSixColor,
            binding.textViewSevenColor,
            binding.textViewEightColor,
            binding.textViewNineColor,
            binding.textViewTenColor
        )
    }

    private fun setAnswers(it: List<Game>) {
        addAllViewsInList()

        for (index in it.indices) {
            val currentGame = it[index]
            val currentTextView = listTextViewQ[index]
            val currentImageView = listImageViewQ[index]
            val currentImageViewNotRight = listImageViewQNotRight[index]
            val currentTextViewNotRight = listTextViewQNotRight[index]
            val currentTextViewColor = listTextViewQColor[index]
            currentTextView.text = currentGame.bookText
            currentImageView.setImageResource(currentGame.selectedBookId!!)
            if (currentGame.bookCover != currentGame.selectedBookId) {
                currentImageViewNotRight.visibility = View.VISIBLE
                currentImageViewNotRight.setImageResource(currentGame.bookCover)
                currentTextViewNotRight.visibility = View.VISIBLE
                currentTextViewColor.setBackgroundResource(R.drawable.wrong_answer)
            } else {
                currentImageViewNotRight.visibility = View.INVISIBLE
                currentTextViewNotRight.visibility = View.INVISIBLE
                currentTextViewColor.setBackgroundResource(R.drawable.right_answer)
            }
        }
    }

    private fun setOnClickListener() {
        binding.buttonAgain.setOnClickListener {
            launchFragment(GameFragment())
            viewModel.updateTable()
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}