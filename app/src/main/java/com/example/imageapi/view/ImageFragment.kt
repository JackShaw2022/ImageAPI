package com.example.imageapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imageapi.databinding.FragmentImageBinding
import com.example.imageapi.model.network.ApiManager
import com.example.imageapi.model.repository.ImageRepository
import com.example.imageapi.view.adapter.ImageAdapter
import com.example.imageapi.viewmodel.ImageViewModel

class ImageFragment: Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding: FragmentImageBinding get() = _binding!!

    private val viewModel: ImageViewModel by activityViewModels {
        ImageViewModel.Factory(ImageRepository(ApiManager()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            viewModel.images.observe(viewLifecycleOwner){image ->
                imageRv.apply {
                    adapter = image?.let { ImageAdapter(it) }
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}