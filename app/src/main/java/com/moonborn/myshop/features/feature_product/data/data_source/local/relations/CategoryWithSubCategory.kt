package com.moonborn.myshop.features.feature_product.data.data_source.local.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.moonborn.myshop.features.feature_product.data.data_source.local.CategoryEntity


data class CategoryWithSubCategory(
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "parentCategory"
    )
    val categories: List<CategoryEntity>
)