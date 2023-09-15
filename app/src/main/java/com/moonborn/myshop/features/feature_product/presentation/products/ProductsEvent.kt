package com.moonborn.myshop.features.feature_product.presentation.products

import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductEntity
import com.moonborn.myshop.features.feature_product.domain.utils.ProductOrder

sealed class ProductsEvent {
    object Refresh: ProductsEvent()
    data class OnSearchQueryChange(val query: String): ProductsEvent()
}