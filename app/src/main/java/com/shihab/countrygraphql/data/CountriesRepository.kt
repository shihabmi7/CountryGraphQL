package com.shihab.countrygraphql.data

import com.apollographql.apollo3.ApolloClient
import com.shihab.spacex.graphql.CountriesQuery
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val apolloClient: ApolloClient
) {
    suspend fun getCountries(): List<CountriesQuery.Country> {
        val response = apolloClient.query(CountriesQuery()).execute()
        return response.data?.countries.orEmpty()
    }
}
