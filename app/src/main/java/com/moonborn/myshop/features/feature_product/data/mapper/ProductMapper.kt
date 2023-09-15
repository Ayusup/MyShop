package com.moonborn.myshop.features.feature_product.data.mapper

import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductEntity
import com.moonborn.myshop.features.feature_product.data.data_source.remote.dto.ProductDto
import com.moonborn.myshop.features.feature_product.domain.models.Product

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        name = productName,
        brand = brand,
        category = category,
        image = image,
        price = price,
        newProduct = newProduct,
        promotionsAndSale = promotionsAndSale,
        popular = popular,
        onSale = onSale,
        amountInStock = amountInStock,
        amountInCart = 0
    )
}

fun Product.toProductEntity() : ProductEntity{
    return ProductEntity(
        id = id,
        productName = name,
        brand = brand,
        category = category,
        image = image,
        price = price,
        newProduct = newProduct,
        promotionsAndSale = promotionsAndSale,
        popular = popular,
        onSale = onSale,
        amountInStock = amountInStock
    )
}

fun ProductDto.toProductEntity() : ProductEntity{
    return ProductEntity(
        id = id ?: "",
        productName = name ?: "",
        brand = brand ?: "",
        category = category ?: "",
        image = image ?: "",
        price = price?.toDouble() ?: 0.00,
        newProduct = new_product?.lowercase() == ("true" ?: false),
        promotionsAndSale = promotions_and_sale?.lowercase() == ("true" ?: false),
        popular = popular?.lowercase() == ("true" ?: false),
        onSale = on_sale?.lowercase() == ("true" ?: false),
        amountInStock = amount_in_stock?.toInt() ?: 0
    )
}