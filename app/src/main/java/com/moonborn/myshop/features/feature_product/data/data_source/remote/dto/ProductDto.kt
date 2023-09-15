package com.moonborn.myshop.features.feature_product.data.data_source.remote.dto

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class ProductDto(
    @field:Json(name = "ProductArticle") val id: String?,
    @field:Json(name = "ProductName") val name: String?,
    @field:Json(name = "ProductBrand") val brand: String?,
    @field:Json(name = "ProductCategory") val category: String?,
    @field:Json(name = "ProductImageUrl") val image: String?,
    @field:Json(name = "ProductPrice") val price: String?,
    @field:Json(name = "NewProduct") val new_product: String?,
    @field:Json(name = "PromotionsAndSale") val promotions_and_sale: String?,
    @field:Json(name = "Popular") val popular: String?,
    @field:Json(name = "OnSale") val on_sale: String?,
    @field:Json(name = "AmountInStock") val amount_in_stock: String?,
)