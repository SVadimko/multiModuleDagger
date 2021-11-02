package com.example.multi.details

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import com.example.multi.details.databinding.FragmentPostsBinding
import com.example.multi.retrofitapi.ResponsePostItem

class PostsRecyclerViewAdapter() :
    ListAdapter<ResponsePostItem, PostsRecyclerViewAdapter.ViewHolder>(PostCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentPostsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: PostsRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: FragmentPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(responsePostItem: ResponsePostItem) {
            binding.itemNumber.text = responsePostItem.title
            binding.content.text = responsePostItem.body
        }
    }

}

private class PostCallback() : DiffUtil.ItemCallback<ResponsePostItem>() {
    override fun areItemsTheSame(oldItem: ResponsePostItem, newItem: ResponsePostItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ResponsePostItem, newItem: ResponsePostItem): Boolean {
        return oldItem.id == newItem.id
    }

}