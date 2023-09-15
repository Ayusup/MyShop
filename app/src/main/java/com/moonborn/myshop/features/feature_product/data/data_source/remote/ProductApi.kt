package com.moonborn.myshop.features.feature_product.data.data_source.remote

import com.moonborn.myshop.features.feature_product.data.data_source.remote.dto.ProductDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("query?function=PRODUCT_STATUS")
    suspend fun getProducts(
        @Query("apikey") apiKey: String = API_KEY
    ): ProductDto


    companion object {
        const val API_KEY = ""
        const val BASE_URL = "https://test.com"
    }

}