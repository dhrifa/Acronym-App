package com.example.acronymapp.ui

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapp.data.model.Lf
import com.example.acronymapp.databinding.AcronymItemBinding

class AcronymAdapter(
    private val data: List<Lf>,
//    val function: (author: String) -> Unit
) :
    RecyclerView.Adapter<AcronymAdapter.ViewHolder>() {

    inner class ViewHolder(private val view: AcronymItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(acronym: Lf/*, position: Int*/) {
            view.acronym = acronym
//            view.layoutAuthor.setOnClickListener {
//                function.invoke(author)
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            AcronymItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.initUI(data[position], position)
        holder.apply { bind(data[position]) }

    }

}