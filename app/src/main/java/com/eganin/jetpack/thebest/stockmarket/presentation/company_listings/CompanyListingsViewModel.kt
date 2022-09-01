package com.eganin.jetpack.thebest.stockmarket.presentation.company_listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eganin.jetpack.thebest.stockmarket.domain.repository.StockRepository
import com.eganin.jetpack.thebest.stockmarket.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewModel @Inject constructor(
    private val repository: StockRepository,
) : ViewModel() {
    var state by mutableStateOf(CompanyListingsState())

    private var searchJob : Job? = null

    init{
        getCompanyListings()
    }

    fun onEvent(event: CompanyListingsEvent) {
        when (event) {
            is CompanyListingsEvent.Refresh -> {
                getCompanyListings(fetchFromRemote = true)
            }
            is CompanyListingsEvent.OnSearchQueryChange -> {
                state= state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000L)
                    getCompanyListings()
                }
            }
        }
    }

    private fun getCompanyListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false,
    ) {
        viewModelScope.launch {
            repository
                .getCompanyListings(query = query, fetchFromRemote = fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    companies = listings,
                                )
                            }
                        }

                        is Resource.Error -> {
                            state = state.copy(
                                isLoading = false,
                                error = result.message,
                            )
                        }

                        is Resource.Loading -> {
                            state = state.copy(isLoading =  result.isLoading)
                        }
                    }
                }
        }
    }
}