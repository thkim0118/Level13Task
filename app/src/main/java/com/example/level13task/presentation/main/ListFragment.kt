package com.example.level13task.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.level13task.R
import com.example.level13task.databinding.FragmentListBinding
import com.example.level13task.presentation.util.decoration.GridSpacingItemDecoration
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        collectUiState()
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                (binding.listRecyclerView.adapter as? PhotoListAdapter)?.submitList(it.photos)
            }
        }
    }

    private fun initRecyclerView() {
        binding.listRecyclerView.apply {
            layoutManager = GridLayoutManager(
                requireContext(),
                2,
                GridLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(GridSpacingItemDecoration(2, 16))

            adapter = PhotoListAdapter(
                onPhotoClick = { index ->
                    findNavController().navigate(
                        R.id.action_listFragment_to_detailFragment,
                        args = Bundle().apply {
                            putInt(PHOTO_INDEX_KEY, index)
                        }
                    )
                },
                onCheckBoxClick = { index, isChecked ->
                    viewModel.updateCheckState(isChecked, index)
                }
            )
        }
    }

    companion object {
        const val PHOTO_INDEX_KEY = "photoIndex"
    }
}