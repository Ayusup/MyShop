package com.moonborn.myshop.features.feature_planned_lists.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.moonborn.myshop.features.feature_planned_lists.data.data_source.local.relations.PlannedListWithProducts

@Dao
interface PlannedListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlannedList(plannedLIst: PlannedListEntity)

    @Transaction
    @Query("SELECT * FROM PlannedListEntity WHERE  plannedListName = :plannedListName")
    suspend fun getProductsOfPlannedList(plannedListName: String): List<PlannedListWithProducts>

    @Query("DELETE FROM PlannedListEntity WHERE plannedListId = :plannedListId")
    suspend fun deletePlannedList(plannedListId: String)



}