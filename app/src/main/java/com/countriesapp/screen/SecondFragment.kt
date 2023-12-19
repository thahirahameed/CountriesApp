package com.countriesapp.screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.countriesapp.R
import com.countriesapp.adapter.CountriesAdapter
import com.countriesapp.databinding.FragmentSecondBinding
import com.countriesapp.domain.remotedata.Countries
import com.countriesapp.domain.remotedata.CountriesItem
import com.countriesapp.domain.usecase.CountriesUseCase
import com.countriesapp.domain.util.Resource
import com.countriesapp.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {


    private var _binding: FragmentSecondBinding? = null
    val viewModel: CountriesViewModel by viewModels()
    val countriesAdapter = CountriesAdapter(mutableListOf())
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { state->
            when (state) {
                is Resource.Loading -> {
                    Toast.makeText(context, "LOADING", Toast.LENGTH_LONG).show()
                }

                is Resource.Success -> countriesAdapter.loadCountryDetails(state.data)
                is Resource.Error -> {
                    Log.e("Error", "")
                }
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = countriesAdapter
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}