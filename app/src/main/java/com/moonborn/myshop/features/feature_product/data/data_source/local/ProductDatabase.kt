package com.moonborn.myshop.features.feature_product.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moonborn.myshop.features.feature_product.data.data_source.local.CategoryEntity
import com.moonborn.myshop.features.feature_planned_lists.data.data_source.local.PlannedListEntity
import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductDao
import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductEntity
import com.moonborn.myshop.features.feature_product.data.data_source.local.relations.ProductCategoryCrossRef
import com.moonborn.myshop.features.feature_planned_lists.data.data_source.local.relations.ProductPlannedListCrossRef

@Database(
    entities = [
        ProductEntity::class,
        CategoryEntity::class,
        ProductCategoryCrossRef::class,
        PlannedListEntity::class,
        ProductPlannedListCrossRef::class
    ],
    version = 1
)
abstract class ProductDatabase: RoomDatabase() {
    abstract val dao: ProductDao
}