package com.example.acronymapp.ui

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapp.data.model.Lf
import com.example.acronymapp.databinding.AcronymItemBinding

class AcronymAdapter(
    private val data: MutableList<Lf> = mutableListOf(),
//    val function: (author: String) -> Unit
) : RecyclerView.Adapter<AcronymAdapter.ViewHolder>() {
    fun updateData(newData: List<Lf>) {
        val callback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = data.size
            override fun getNewListSize(): Int = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                data[oldItemPosition].lf == newData[newItemPosition].lf

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                data[oldItemPosition].lf?.equals(newData[newItemPosition].lf) ?: false
        }
        val result = DiffUtil.calculateDiff(callback)
        data.clear()
        data.addAll(newData)

        result.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(
        private val view: AcronymItemBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(acronym: Lf/*, position: Int*/) {
            view.acronym = acronym
            view.varsBtn.setOnClickListener {
//                function.invoke(author)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            AcronymItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.apply { bind(data[position]) }
        holder.bind(data[position])

    }

}