package com.moonborn.myshop.features.feature_product.data.data_source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = false)
    val categoryName: String,
    val parentCategory: String
)