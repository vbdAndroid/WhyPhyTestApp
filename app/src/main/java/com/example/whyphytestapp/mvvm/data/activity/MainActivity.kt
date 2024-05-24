package com.example.whyphytestapp.mvvm.data.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.whyphytestapp.R
import com.example.whyphytestapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel: userActivityVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

    }

    fun bindObservers() {
//        viewModel.screenResultResposne.observe(this) {
//            Log.e("API", "bindObservers Screen: ${it.toString()}")
//            viewModel.screenListAdapter = ScreenListAdapter(context = baseContext,
//                it?.screens as ArrayList<screenData>, object : MovieItemClickListener {
//                    override fun movieItemClickListener(position: Int) {
////                        val action =
////                            PopularMoviesFragmentDirections.actionPopularMovieFragmentToMovieDetailsFragment(
////                                movie.id
////                            )
////                        findNavController().navigate(action)
//                    }
//                }
//
//            )
//            binding.recycleScreenList.adapter = viewModel.screenListAdapter
//        }
    }
}