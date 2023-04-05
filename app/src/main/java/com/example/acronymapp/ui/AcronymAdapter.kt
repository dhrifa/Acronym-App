package com.example.acronymapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapp.databinding.AcronymItemBinding

class AcronymAdapter(
    private val data: List<String>,
//    val function: (author: String) -> Unit
) :
    RecyclerView.Adapter<AcronymAdapter.ViewHolder>() {

    inner class ViewHolder(private val view: AcronymItemBinding) :
        RecyclerView.ViewHolder(view.root) {
//        fun initUI(author: String, position: Int) {
//            view.tvAuthor.text = author
//            view.layoutAuthor.setOnClickListener {
//                function.invoke(author)
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            AcronymItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.initUI(data[position], position)
    }

}