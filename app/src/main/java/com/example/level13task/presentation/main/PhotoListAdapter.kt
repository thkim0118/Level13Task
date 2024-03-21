package com.example.level13task.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.level13task.databinding.ItemPhotoBinding
import com.example.level13task.presentation.main.model.PhotoUiModel

class PhotoListAdapter(
    private val onPhotoClick: (index: Int) -> Unit,
    private val onCheckBoxClick: (index: Int, isChecked: Boolean) -> Unit
) : ListAdapter<PhotoUiModel, PhotoListAdapter.PhotoListViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
        return PhotoListViewHolder(
            binding = ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onPhotoClick = onPhotoClick,
            onCheckBoxClick = onCheckBoxClick,
        )
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PhotoListViewHolder(
        private val binding: ItemPhotoBinding,
        private val onPhotoClick: (index: Int) -> Unit,
        private val onCheckBoxClick: (index: Int, isChecked: Boolean) -> Unit
    ) : ViewHolder(binding.root) {
        fun bind(item: PhotoUiModel) {
            binding.imageView.apply {
                Glide.with(binding.root)
                    .load(item.imageUrl)
                    .centerCrop()
                    .into(this)

                setOnClickListener {
                    onPhotoClick(adapterPosition)
                }
            }

            if (binding.checkBox.isChecked != item.isChecked) {
                binding.checkBox.isChecked = item.isChecked
            }

            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                onCheckBoxClick.invoke(adapterPosition, isChecked)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<PhotoUiModel>() {
            override fun areItemsTheSame(oldItem: PhotoUiModel, newItem: PhotoUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhotoUiModel, newItem: PhotoUiModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}