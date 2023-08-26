package com.gap.bookquiz.finfishFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gap.bookquiz.R
import com.gap.bookquiz.database.AppDatabase
import com.gap.bookquiz.databinding.FragmentFinishBinding
import com.gap.bookquiz.gameFragment.GameFragment


class FinishFragment : Fragment() {

    private var _binding: FragmentFinishBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FinishViewModel
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
        viewModel.numberLiveData.observe(requireActivity()) {
            val text = getString(R.string.number_of_correct_answer, it.toString())
            binding.title.text = text
            Log.d("testtest", "onViewCreated: $text")

        }


        setOnClickListener()

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