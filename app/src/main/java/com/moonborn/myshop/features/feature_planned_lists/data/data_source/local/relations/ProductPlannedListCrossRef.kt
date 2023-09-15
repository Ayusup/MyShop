package com.moonborn.myshop.features.feature_planned_lists.data.data_source.local.relations

import androidx.room.Entity

@Entity(primaryKeys = ["productName", "plannedListName"])
data class ProductPlannedListCrossRef(
    val productName: String,
    val plannedListName: String
)
