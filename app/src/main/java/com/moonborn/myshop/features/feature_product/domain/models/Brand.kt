package com.moonborn.myshop.features.feature_product.domain.models

data class Brand(
    val id: String,
    val name: String,
    val products: List<Product>
)
