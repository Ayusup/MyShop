package com.moonborn.myshop.features.feature_product.presentation.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moonborn.myshop.features.feature_product.domain.repository.ProductRepository
import com.moonborn.myshop.ui.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel() {

    private var state by mutableStateOf(ProductsState())

    private var searchJob: Job? = null

    fun onEvent(event: ProductsEvent){
        when(event){
            is ProductsEvent.Refresh -> {
                getProducts(fetchFromRemote = true)
            }
            is ProductsEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getProducts()
                }
            }
        }
    }

    private fun getProducts(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ){
        viewModelScope.launch {
            repository
                .getProduct(fetchFromRemote, query)
                .collect{result ->
                    when(result){
                        is Resource.Success -> {
                            result.data?.let {loadedProducts ->
                                state = state.copy(
                                    products = loadedProducts
                                )
                            }
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = state.isLoading)
                        }
                        is Resource.Error -> Unit
                    }
                }
        }
    }

}