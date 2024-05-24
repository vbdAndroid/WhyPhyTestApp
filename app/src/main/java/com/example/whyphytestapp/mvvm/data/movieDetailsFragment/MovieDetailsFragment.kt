package com.example.movielistapp.mvvm.views.fragment.movieDetailsFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whyphytestapp.mvvm.data.dataInterface.MovieItemClickListener
import com.example.whyphytestapp.databinding.SeatSelectionMainBinding
import com.example.whyphytestapp.mvvm.data.movieDetailsFragment.SeatSelectionListAdapter
//import com.example.movielistapp.dao.AppDatabase
//import com.example.movielistapp.dao.movieTables
//import com.example.movielistapp.dao.repository.MovieRepository
//import com.example.movielistapp.databinding.MovieDetailsFragmentBinding
//import com.example.movielistapp.network.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    //    private lateinit var adapter: MoviesAdapter
    val viewModel: MovieDetailsVM by viewModels()

    private lateinit var binding: SeatSelectionMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SeatSelectionMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleScreenList.layoutManager =
            LinearLayoutManager(requireContext())
        viewModel.getUserSeats()
        bindObservers()

    }

  private fun bindObservers() {
        viewModel.seatResultResposne.observe(viewLifecycleOwner) {
            Log.e("API", "bindObservers Screen: ${it.toString()}")
            viewModel.screenListAdapter = context?.let { it1 ->
                SeatSelectionListAdapter(
                    context = it1,
                    it?.seat_layout, object : MovieItemClickListener {
                        override fun movieItemClickListener(position: Int) {

                        }
                    }

                )
            }
            binding.recycleScreenList.adapter = viewModel.screenListAdapter
        }


    }


}
