package com.moonborn.myshop.features.feature_product.domain.repository

import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductEntity
import com.moonborn.myshop.features.feature_product.domain.models.Product
import com.moonborn.myshop.ui.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {


    suspend fun getProduct(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Product>>>



    fun getProducts(): Flow<List<ProductEntity>>


    suspend fun getProductById(id: String): ProductEntity?

    suspend fun insertProduct(product: ProductEntity)

    suspend fun deleteProduct(product: ProductEntity)

}