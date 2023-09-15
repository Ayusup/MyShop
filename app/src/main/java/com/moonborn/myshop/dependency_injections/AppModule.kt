package com.moonborn.myshop.dependency_injections

import android.app.Application
import androidx.room.Room
import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductDatabase
import com.moonborn.myshop.features.feature_product.data.data_source.remote.ProductApi
import com.moonborn.myshop.features.feature_product.data.repository.ProductRepositoryImpl
import com.moonborn.myshop.features.feature_product.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return Retrofit.Builder()
            .baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideProductDatabase(app: Application): ProductDatabase {
        return Room.databaseBuilder(
            app,
            ProductDatabase::class.java,
            "product.db"
        ).build()
    }


}