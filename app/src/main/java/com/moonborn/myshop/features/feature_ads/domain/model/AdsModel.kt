package com.moonborn.myshop.features.feature_ads.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AdsModel(
    val id: String,
    val name: String,
    val image: String,
    @PrimaryKey val uid: Int? = null
)



//@Entity
//data class Product(
//    val id: String,
//    val name: String,
//    val brand: String,
//    val category: String,
//    val image: String,
//    val price: Double,
//    val newProduct: Boolean,
//    val promotionsAndSale: Boolean,
//    val popular: Boolean,
//    val onSale: Boolean,
//    val amountInStock: Int,
//    @PrimaryKey val uid: Int? = null
//){
//    companion object{
//
//    }
//}