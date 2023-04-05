package com.example.acronymapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapp.R
import com.example.acronymapp.databinding.ActivityMainBinding
import com.example.acronymapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val acronymViewModel: AcronymViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @BindingAdapter("meaningList")
    fun bindNewMeanings(recyclerView: RecyclerView, uiState: UIState) {
        if (uiState is UIState.SUCCESS) {
        acronymViewModel.getMeaning("HMM")
        }
    }
}