package com.moonborn.myshop.features.feature_product.domain.utils

sealed class ProductOrder(val orderType: OrderType) {
    class Price(orderType: OrderType): ProductOrder(orderType)
}