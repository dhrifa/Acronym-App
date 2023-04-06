package com.example.acronymapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapp.databinding.ActivityMainBinding
import com.example.acronymapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    //    private var _binding: ActivityMainBinding? = null
//    private val binding get() = _binding!!
    companion object {
        private var _binding: ActivityMainBinding? = null
        private val binding get() = _binding!!
//        private val binding by lazy {
//            ActivityMainBinding.inflate(layoutInflater)
//        }
    }

    //    private val acronymViewModel: AcronymViewModel by viewModels()
    private val acronymViewModel by lazy {
        ViewModelProvider(this)[AcronymViewModel::class.java] //in a frag, owner=activity =>shared viewmodel
    }

    private val acronymAdapter by lazy { AcronymAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.acronymRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = acronymAdapter
        }

        acronymViewModel.getMeaning("HMM")//should be a var
    }


    @BindingAdapter("meaningList")
    fun bindNewMeanings(
        recyclerView: RecyclerView,
//        view: ProgressBar,
        uiState: UIState
    ) {
        when (uiState) {
            is UIState.SUCCESS -> {
                recyclerView.visibility = View.VISIBLE
//                view.visibility = View.GONE
                acronymAdapter.updateData(uiState.response.firstOrNull()?.lfs ?: emptyList())
            }
            is UIState.LOADING -> {
                recyclerView.visibility = View.GONE
//                view.visibility = View.VISIBLE
            }
            is UIState.ERROR -> {
                recyclerView.visibility = View.GONE
//                view.visibility = View.GONE
                AlertDialog.Builder(this)
                    .setTitle("Error Occurred")
                    .setMessage(uiState.error.localizedMessage)
                    .setNegativeButton("DISMISS") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }
        }
    }

    @BindingAdapter("state")
    fun bindNewMeanings(
        view: ProgressBar,
        state: UIState
    ) {
        if (state is UIState.LOADING) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }


}