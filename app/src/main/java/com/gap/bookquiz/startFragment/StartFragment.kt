package com.gap.bookquiz.startFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gap.bookquiz.gameFragment.GameFragment
import com.gap.bookquiz.R
import com.gap.bookquiz.database.AppDatabase
import com.gap.bookquiz.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: StartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getInstance(application).gameDao
        val viewModelFactory = StartViewModelFactory(dao)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(StartViewModel::class.java)

        viewModel.addQuestions(requireContext())

        clickListener()
    }

    private fun clickListener() {
        binding.buttonPlay.setOnClickListener {
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
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}