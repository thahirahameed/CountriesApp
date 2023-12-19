package com.countriesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.countriesapp.databinding.CountryCardBinding
import com.countriesapp.domain.remotedata.CountriesItem

class CountriesAdapter(
    private val countryList: MutableList<CountriesItem>
): RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    lateinit var binding: CountryCardBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountriesAdapter.CountriesViewHolder =
        CountriesViewHolder(
            CountryCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CountriesAdapter.CountriesViewHolder, position: Int) {
        holder.bindView(countryList[position])
    }

    override fun getItemCount(): Int = countryList.size

    fun loadCountryDetails(countryValues: List<CountriesItem>){
        countryList.addAll(countryValues)
        notifyDataSetChanged()
    }

    inner class CountriesViewHolder(private val binding: CountryCardBinding):RecyclerView.ViewHolder(binding.root){

        fun bindView(countryDetails: CountriesItem){
            binding.countryNameRegion.text = (countryDetails.name + "," + countryDetails.region).toString()
            binding.countryCode.text = countryDetails.code
            binding.countryCapital.text = countryDetails.capital
        }
    }

}