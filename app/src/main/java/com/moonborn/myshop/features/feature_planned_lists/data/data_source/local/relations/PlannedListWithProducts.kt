package com.moonborn.myshop.features.feature_planned_lists.data.data_source.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.moonborn.myshop.features.feature_planned_lists.data.data_source.local.PlannedListEntity
import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductEntity


data class PlannedListWithProducts(
    @Embedded val plannedList: PlannedListEntity,
    @Relation(
        parentColumn = "plannedListName",
        entityColumn = "productName"
    )
    val categories: List<ProductEntity>
)
