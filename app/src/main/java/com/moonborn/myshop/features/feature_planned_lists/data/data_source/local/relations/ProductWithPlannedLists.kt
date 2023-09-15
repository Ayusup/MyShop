package com.moonborn.myshop.features.feature_planned_lists.data.data_source.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.moonborn.myshop.features.feature_planned_lists.data.data_source.local.PlannedListEntity
import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductEntity


data class ProductWithPlannedLists(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "productName",
        entityColumn = "plannedListName"
    )
    val categories: List<PlannedListEntity>
)