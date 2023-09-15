package com.moonborn.myshop.features.feature_planned_lists.data.data_source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlannedListEntity(
    val plannedListId: String,
    @PrimaryKey(autoGenerate = false)
    val plannedListName: String,
    val date: String,
)