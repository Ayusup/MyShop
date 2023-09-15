package com.moonborn.myshop.features.feature_product.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.moonborn.myshop.features.feature_product.data.data_source.local.relations.CategoryWithProducts
import com.moonborn.myshop.features.feature_product.data.data_source.local.relations.CategoryWithSubCategory
import com.moonborn.myshop.features.feature_planned_lists.data.data_source.local.relations.PlannedListWithProducts
import com.moonborn.myshop.features.feature_product.data.data_source.local.relations.ProductCategoryCrossRef

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(productList: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCategoryCrossRef(crossRef: ProductCategoryCrossRef)

    @Query("DELETE FROM ProductEntity")
    suspend fun clear()

    @Query(
        """
            SELECT *
            FROM ProductEntity
            WHERE LOWER(productName) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(:query) == LOWER(category) OR LOWER(:query) == LOWER(brand)
        """
    )
    suspend fun searchProduct(query: String): List<ProductEntity>

    @Transaction
    @Query("SELECT * FROM categoryentity WHERE categoryName = :categoryName")
    suspend fun getProductsOfCategory(categoryName: String): List<CategoryWithProducts>


    @Transaction
    @Query("SELECT * FROM categoryentity WHERE categoryName = :categoryName")
    suspend fun getCategoryWithSubCategories(categoryName: String): List<CategoryWithSubCategory>


}