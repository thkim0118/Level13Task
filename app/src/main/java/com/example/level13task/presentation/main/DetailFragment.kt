package com.example.level13task.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.level13task.databinding.FragmentDetailBinding
import com.example.level13task.presentation.main.ListFragment.Companion.PHOTO_INDEX_KEY
import com.example.level13task.presentation.main.model.PhotoUiModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(PHOTO_INDEX_KEY)?.let { index ->
            viewModel.getPhoto(index)?.let { photoUiModel ->
                initViews(photoUiModel, index)
            }
        }
    }

    private fun initViews(photoUiModel: PhotoUiModel, index: Int) {
        Glide.with(binding.root)
            .load(photoUiModel.imageUrl)
            .fitCenter()
            .into(binding.detailImageView)

        binding.detailCheckBox.isChecked = photoUiModel.isChecked

        binding.detailCheckBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateCheckState(isChecked, index)
        }
    }

}