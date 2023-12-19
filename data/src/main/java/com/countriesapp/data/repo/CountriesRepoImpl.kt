package com.countriesapp.data.repo

import com.countriesapp.data.CountriesApi
import com.countriesapp.domain.remotedata.Countries
import com.countriesapp.domain.repo.CountriesRepo
import javax.inject.Inject

class CountriesRepoImpl @Inject constructor(
    private val api:CountriesApi
): CountriesRepo {

    override suspend fun getCountries(): Countries {
        return api.getCountries()
    }
}