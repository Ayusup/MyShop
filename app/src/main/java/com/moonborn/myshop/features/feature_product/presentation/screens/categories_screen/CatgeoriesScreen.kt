package com.moonborn.myshop.features.feature_product.presentation.screens.categories_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moonborn.myshop.R
import com.moonborn.myshop.features.feature_product.domain.models.Category
import com.moonborn.myshop.features.feature_product.presentation.products.components.CategoryModel
import com.moonborn.myshop.features.feature_product.presentation.util.navigation.ScreensRoutes
import com.moonborn.myshop.features.feature_product.presentation.screens.main_screen.productList
import com.moonborn.myshop.ui.theme.BGColor
import com.moonborn.myshop.ui.theme.ColorAccent

var categoryItemsList = listOf<Category>()

fun mockCategories():List<Category>{
    var categoryItems = mutableListOf<Category>()
    for (i in 1..18){
        categoryItems.add(Category(
            name = "Category" + "${i}",
            "",
            mutableListOf(),
            productsList = productList()
        ))
    }
    categoryItemsList = categoryItems
    return categoryItems
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoriesTopBar(
    scrollBehaviour: TopAppBarScrollBehavior,
    function: () -> Unit
){
    TopAppBar(
        title = {

        },
        scrollBehavior = scrollBehaviour,
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
                        text = "Categories",
                        fontSize = 20.sp,
                        color = ColorAccent)

                }
            }

        }

    )
}


@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CategoriesScreenNavigation(
    function: () -> Unit
){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreensRoutes.Categories.route, route = ScreensRoutes.CartScreenRootNav.route){

        composable(ScreensRoutes.Categories.route){
            CategoriesScreen(navController, function)
        }


    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(navController: NavHostController, function: () -> Unit) {
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)

    Scaffold(
        modifier = Modifier,
        topBar = {
            Surface(shadowElevation = 3.dp) {
                CategoriesTopBar(scrollBehavior, function)
            }
        },
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(BGColor)
            .padding(padding)){
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                columns = GridCells.Fixed(2),
                content = {
                    items(mockCategories().size){ i ->
                        Box(modifier = Modifier.padding(horizontal = 0.dp, vertical = 10.dp),
                            contentAlignment = Alignment.Center){
                            CategoryModel(
                                contentDescription = mockCategories()[i].name,
                                name = mockCategories()[i].name
                            )
                        }
                    }
                }
            )
        }
    }
    
}