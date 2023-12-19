package com.countriesapp.domain.repo

import com.countriesapp.domain.remotedata.Countries


interface CountriesRepo {

    suspend fun getCountries(): Countries
}
