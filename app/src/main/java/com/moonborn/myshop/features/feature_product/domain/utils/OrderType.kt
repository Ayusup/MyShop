package com.moonborn.myshop.features.feature_product.domain.utils

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
