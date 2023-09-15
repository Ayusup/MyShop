package com.moonborn.myshop.features.feature_product.data.data_source.local.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.moonborn.myshop.features.feature_product.data.data_source.local.CategoryEntity
import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductEntity


data class ProductWithCategories(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "productName",
        entityColumn = "categoryName",
        associateBy = Junction(ProductCategoryCrossRef::class)
    )
    val categories: List<CategoryEntity>
)