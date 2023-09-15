package com.moonborn.myshop.features.feature_product.domain.models

data class Category(
    val name: String,
    val parentCategory: String,
    var childCategories: MutableList<Category>,
    val productsList: List<Product>
)