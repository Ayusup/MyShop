package com.moonborn.myshop.features.feature_planned_lists.domain.model

import com.moonborn.myshop.features.feature_product.domain.models.Product
import java.time.LocalDate

data class PlannedList(
    val id: String,
    val plannedListName: String,
    val date: LocalDate,
    val products: List<Product>
)
