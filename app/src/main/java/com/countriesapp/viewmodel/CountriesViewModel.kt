package com.countriesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countriesapp.domain.remotedata.CountriesItem
import com.countriesapp.domain.usecase.CountriesUseCase
import com.countriesapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesUseCase: CountriesUseCase,
): ViewModel(){

    private var _state : MutableLiveData<Resource<List<CountriesItem>>> = MutableLiveData(Resource.Loading())
    val state : LiveData<Resource<List<CountriesItem>>> get() = _state


    init {
        getCountryList()
    }

    fun getCountryList(){
        countriesUseCase().onEach { result ->

            when(result){
                is Resource.Success -> {
                    _state.postValue(result)
                }
                is Resource.Error -> {
                    _state.postValue(Resource.Error("An unexpected error occurred"))
                }
                is Resource.Loading -> {
                    _state.postValue(Resource.Loading())
                }

                else -> {
            _state.postValue(Resource.Error("An unexpected error occurred"))
                }
            }
        }.launchIn(viewModelScope)
    }
}