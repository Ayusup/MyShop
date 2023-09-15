package com.moonborn.myshop.features.feature_product.data.data_source.local.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.moonborn.myshop.features.feature_product.data.data_source.local.CategoryEntity
import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductEntity

data class CategoryWithProducts(
    @Embedded
    val category: CategoryEntity,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "productName",
        associateBy = Junction(ProductCategoryCrossRef::class)
    )
    val products: List<ProductEntity>

)
