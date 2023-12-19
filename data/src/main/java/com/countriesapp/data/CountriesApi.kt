package com.countriesapp.data

import com.countriesapp.domain.remotedata.Countries
import retrofit2.http.GET

interface CountriesApi {

    @GET("32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): Countries

    //https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json

    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/"
    }
}