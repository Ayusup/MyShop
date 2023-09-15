package com.moonborn.myshop.features.feature_product.presentation.products

import com.moonborn.myshop.features.feature_product.domain.models.Product

data class ProductsState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)

