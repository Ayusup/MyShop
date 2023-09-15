package com.moonborn.myshop.features.feature_product.presentation.screens.cart_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moonborn.myshop.R
import com.moonborn.myshop.features.feature_product.presentation.products.components.ProductsInCart
import com.moonborn.myshop.features.feature_product.presentation.util.scrollEnabled
import com.moonborn.myshop.features.feature_product.presentation.screens.main_screen.productList
import com.moonborn.myshop.ui.theme.BGColor
import com.moonborn.myshop.ui.theme.ColorAccent
import com.moonborn.myshop.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopBar(
//    scrollBehaviour: TopAppBarScrollBehavior
    function: () -> Unit
){
    TopAppBar(
        title = {

        },
//        scrollBehavior = scrollBehaviour,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        navigationIcon = {},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically


            ){
                Box(modifier = Modifier.fillMaxWidth()){
                    Icon(painter = painterResource(id = R.drawable.person),
                        contentDescription = "profile button",
                        tint = ColorAccent,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterStart)
                            .clickable { function() }
                    )
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Cart",
                        fontSize = 20.sp,
                        color = ColorAccent
                    )

                }
            }
        }
    )
}


fun LazyListScope.ProductsList(modifier: Modifier = Modifier){

        items(productList().size) { index ->
            val product = productList().get(index)
            ProductsInCart(name = product.name, price = product.price)
        }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(parentPadding: PaddingValues, modifier: Modifier = Modifier, function: () -> Unit) {
//    val appBarState = rememberTopAppBarState()
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)

//    Scaffold(
//        modifier = Modifier.padding(parentPadding),
//        topBar = {
//            Surface(shadowElevation = 3.dp) {
//                CartTopBar(scrollBehavior)
//            }
//        },
//    ) { padding ->
        Box(modifier = modifier
            .fillMaxSize()
            .background(BGColor)
            .padding(parentPadding)){
            Box(modifier = Modifier.padding(top = 10.dp)) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item {
                        CartTopBar(function)
                        Row(
                            modifier = Modifier
                                .padding(vertical = 20.dp, horizontal = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${productList().size} item(s)",
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp
                            )
                            Row() {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(id = R.drawable.trash),
                                    contentDescription = "Delete All items",
                                    tint = Green
                                )
                                Text(
                                    text = "Delete",
                                    color = Green,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp
                                )
                            }
                        }

                    }
                    ProductsList(modifier)
                }
            }
        }
//    }
}