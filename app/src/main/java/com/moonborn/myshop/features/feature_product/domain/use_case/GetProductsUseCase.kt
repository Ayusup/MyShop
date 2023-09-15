package com.moonborn.myshop.features.feature_product.domain.use_case

import com.moonborn.myshop.features.feature_product.data.data_source.local.ProductEntity
import com.moonborn.myshop.features.feature_product.domain.repository.ProductRepository
import com.moonborn.myshop.features.feature_product.domain.utils.OrderType
import com.moonborn.myshop.features.feature_product.domain.utils.ProductOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProductsUseCase(
    private val repository: ProductRepository
) {

    operator fun invoke(productOrder: ProductOrder = ProductOrder.Price(OrderType.Descending)
    ): Flow<List<ProductEntity>>{
        return repository.getProducts().map { products ->
            when(productOrder.orderType){
                is OrderType.Ascending -> {
                    products.sortedBy { it.price }
                }
                is OrderType.Descending -> {
                    products.sortedByDescending { it.price }
                }
            }
        }
    }

}