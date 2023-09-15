package com.moonborn.myshop.features.feature_product.data.repository

import android.app.Application
import com.moonborn.myshop.R
import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductDatabase
import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductEntity
import com.moonborn.myshop.features.feature_product.data.data_source.remote.ProductApi
import com.moonborn.myshop.features.feature_product.data.mapper.toProduct
import com.moonborn.myshop.features.feature_product.data.mapper.toProductEntity
import com.moonborn.myshop.features.feature_product.domain.models.Product
import com.moonborn.myshop.features.feature_product.domain.repository.ProductRepository
import com.moonborn.myshop.ui.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi,
    private val db: ProductDatabase,
    private val appContext: Application
): ProductRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
        println("repository created. The app name is $appName")
    }

    private val dao = db.dao

    override suspend fun getProduct(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Product>>> {
        return flow {
            emit(Resource.Loading(true))
            val localProducts = dao.searchProduct(query)
            emit(Resource.Success(
                data = localProducts.map { it.toProduct() }
            ))

            val isDBEmpty = localProducts.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDBEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteProducts = try {
                val response = api.getProducts()
                Resource.Success(response.toProductEntity())
            } catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't Load Data"))
                null
            } catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't Load Data"))
                null
            }

            remoteProducts?.let { product ->
                dao.clear()
                product.data?.let {
                    dao.insertProduct(
                        it
                    )
                }
                emit(Resource.Success(
                    data = dao
                        .searchProduct("")
                        .map { it.toProduct() }
                ))
                emit(Resource.Loading(false))
            }

        }
    }








    override fun getProducts(): Flow<List<ProductEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductById(id: String): ProductEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun insertProduct(product: ProductEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(product: ProductEntity) {
        TODO("Not yet implemented")
    }

}