package com.countriesapp.domain.usecase

import com.countriesapp.domain.remotedata.CountriesItem
import com.countriesapp.domain.repo.CountriesRepo
import com.countriesapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CountriesUseCase @Inject constructor(
    private val repository: CountriesRepo
) {
    operator fun invoke(): Flow<Resource<List<CountriesItem>>> = flow{
        try {
            emit(Resource.Loading(true))
            val countriesData = repository.getCountries().map { it }
            emit(Resource.Success(countriesData))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }catch ( e: IOException){
            emit(Resource.Error("Couldn't  reach server. Check your internet connection."))
        }
    }
}