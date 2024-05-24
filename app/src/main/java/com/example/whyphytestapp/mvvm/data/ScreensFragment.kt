package com.example.whyphytestapp.mvvm.data

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.whyphytestapp.mvvm.data.dataInterface.MovieItemClickListener
import com.example.whyphytestapp.databinding.MovieScreensMainBinding
import com.example.whyphytestapp.mvvm.data.activity.userActivityVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreensFragment : Fragment() {


    val viewModel: userActivityVM by viewModels()
    lateinit var binding: MovieScreensMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieScreensMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleScreenList.layoutManager =
            GridLayoutManager(requireContext(), 2) // Set 2 columns

        viewModel.getUserScreens()
        bindObservers()


    }

    fun bindObservers() {
        viewModel.screenResultResposne.observe(this) {
            Log.e("API", "bindObservers Screen: ${it.toString()}")
            viewModel.screenListAdapter = context?.let { it1 ->
                ScreenListAdapter(context = it1,
                    it?.screens as ArrayList<screenData>, object : MovieItemClickListener {
                        override fun movieItemClickListener(position: Int) {
                            val action =
                                ScreensFragmentDirections.actionMainScreenFragmentToMovieDetailsFragment()

                            findNavController().navigate(action)
                        }
                    }

                )
            }
            binding.recycleScreenList.adapter = viewModel.screenListAdapter
        }
    }


}
