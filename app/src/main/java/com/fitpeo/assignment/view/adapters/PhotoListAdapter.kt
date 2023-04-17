package com.fitpeo.assignment.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fitpeo.assignment.`interface`.OnItemClickListener
import com.fitpeo.assignment.databinding.ItemPhotoListBinding
import com.fitpeo.assignment.model.data.PhotoModelList
import com.fitpeo.assignment.model.data.PhotoModelListItem
import com.fitpeo.assignment.view.imageFromUrl

class PhotoListAdapter(val itemClickListener: OnItemClickListener<PhotoModelListItem>):ListAdapter<PhotoModelListItem,PhotoListAdapter.ItemViewHolder>(ComparatorDiffUtil()){
    inner class ItemViewHolder(private val binding: ItemPhotoListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(photo:PhotoModelListItem){
            binding.cardView.setOnClickListener { itemClickListener.onItemClick(photo) }
            binding.tvTitle.text = photo.title
            binding.imageViewThumbnail.imageFromUrl(photo.thumbnailUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemPhotoListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo = getItem(position)
        photo?.let {
            holder.bind(it)
        }
    }

    class ComparatorDiffUtil: DiffUtil.ItemCallback<PhotoModelListItem>(){
        override fun areItemsTheSame(
            oldItem: PhotoModelListItem,
            newItem: PhotoModelListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PhotoModelListItem,
            newItem: PhotoModelListItem
        ): Boolean {
            return oldItem == newItem
        }

    }
}