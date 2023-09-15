package com.moonborn.myshop.features.feature_planned_lists.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.moonborn.myshop.features.feature_planned_lists.data.data_source.local.PlannedListEntity
import com.moonborn.myshop.features.feature_planned_lists.domain.model.PlannedList
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun PlannedListEntity.toPlannedListModel(): PlannedList {
    val pattern = "yyyy-MMM-dd"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val LocalDate = LocalDate.parse(date, formatter)
    return PlannedList(
        id = plannedListId,
        plannedListName = plannedListName,
        date = LocalDate,
        products = listOf()
    )
}


@RequiresApi(Build.VERSION_CODES.O)
fun PlannedList.toPlannedListEntity(): PlannedListEntity {
    // pattern - yyyy-MMM-dd
    return PlannedListEntity(
        plannedListId = id,
        plannedListName = plannedListName,
        date = "${date.year}-${date.month}-${date.dayOfMonth}"
    )
}