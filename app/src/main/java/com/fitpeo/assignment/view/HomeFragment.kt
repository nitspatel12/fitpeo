package com.fitpeo.assignment.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fitpeo.assignment.PhotoApplication
import com.fitpeo.assignment.R
import com.fitpeo.assignment.`interface`.OnItemClickListener
import com.fitpeo.assignment.databinding.FragmentHomeBinding
import com.fitpeo.assignment.model.data.PhotoModelList
import com.fitpeo.assignment.model.data.PhotoModelListItem
import com.fitpeo.assignment.utils.NetworkResult
import com.fitpeo.assignment.view.adapters.PhotoListAdapter
import com.fitpeo.assignment.viewmodel.MainViewModel
import com.fitpeo.assignment.viewmodel.MainViewModelFactory
import com.fitpeo.assignment.viewmodel.SharedViewModel
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: PhotoListAdapter

    private lateinit var onItemClickListener: OnItemClickListener<PhotoModelListItem>

    val TAG = HomeFragment::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()

        setupView()

        (activity?.application as PhotoApplication).applicationComponent.inject(this)

        mainViewModel = ViewModelProvider(this,mainViewModelFactory)[MainViewModel::class.java]

        mainViewModel.photos.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success -> {
                    adapter.submitList(it.data)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                }
            }
        })

        mainViewModel.loading.observe(viewLifecycleOwner){ loading ->
            if(loading){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        }

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }

    private fun setupView(){
        adapter = PhotoListAdapter(onItemClickListener)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.recyclerView.adapter = adapter

        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener{
                v, scrollX, scrollY, oldScrollX, oldScrollY ->
            Log.d(TAG,"OnScrollChanged : $v : $scrollY")
            if(scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight){
                mainViewModel.fetchPhotos()
            }
        })
    }

    private fun setupListener(){
        onItemClickListener = object : OnItemClickListener<PhotoModelListItem>{
            override fun onItemClick(model: PhotoModelListItem) {
                Log.d("MainActivity","Model : ${model.toString()}")
                sharedViewModel.selectedItem(model)
                gotoDetailFragment()
            }
        }
    }

    private fun gotoDetailFragment(){
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }
}