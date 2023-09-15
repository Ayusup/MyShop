package com.moonborn.myshop.features.feature_product.data.data_source.local.relations

import androidx.room.Embedded
import androidx.room.Entity
import com.moonborn.myshop.features.feature_product.data.data_source.local.CategoryEntity

@Entity(primaryKeys = ["productName", "categoryName"])
data class ProductCategoryCrossRef(
    val productName: String,
    val categoryName: String
)