package com.moonborn.myshop.features.feature_product.data.data_source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    val id: String,
    @ColumnInfo(name = "productName") val productName: String,
    val brand: String,
    val category: String,
    val image: String,
    val price: Double,
    val newProduct: Boolean = false,
    val promotionsAndSale: Boolean = false,
    val popular: Boolean = false,
    val onSale: Boolean = false,
    val amountInStock: Int,
    @PrimaryKey val uid: Int? = null
){
    companion object{

    }
}
