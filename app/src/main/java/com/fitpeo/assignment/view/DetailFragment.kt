package com.fitpeo.assignment.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fitpeo.assignment.R
import com.fitpeo.assignment.databinding.ActivityDetailBinding
import com.fitpeo.assignment.databinding.FragmentDetailBinding
import com.fitpeo.assignment.viewmodel.SharedViewModel

class DetailFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        sharedViewModel.selectedItem.observe(viewLifecycleOwner){
            Log.d("DetailActivity","Sharedviewmodel Observer : ${it.toString()}")
            binding.tvTitle.text = it.title
            binding.imageView.imageFromUrl(it.url)
        }

        binding.lifecycleOwner = this
        binding.viewModel = sharedViewModel
    }
}