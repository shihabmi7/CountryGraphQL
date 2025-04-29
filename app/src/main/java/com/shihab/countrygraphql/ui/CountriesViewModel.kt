package com.shihab.countrygraphql.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
 import com.apollographql.apollo3.ApolloClient
import com.shihab.spacex.graphql.CountriesQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val apolloClient: ApolloClient
) : ViewModel() {

    private val _countries = MutableStateFlow<List<CountriesQuery.Country>>(emptyList())
    val countries: StateFlow<List<CountriesQuery.Country>> = _countries

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            try {
                val response = apolloClient.query(CountriesQuery()).execute()
                _countries.value = response.data?.countries.orEmpty()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
