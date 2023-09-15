package com.moonborn.myshop.features.feature_product.domain.models

data class Product(
    val id: String,
    val name: String,
    val brand: String,
    val category: String,
    val image: String,
    val price: Double,
    val newProduct: Boolean,
    val promotionsAndSale: Boolean,
    val popular: Boolean,
    val onSale: Boolean,
    val amountInStock: Int,
    var amountInCart: Int
)
