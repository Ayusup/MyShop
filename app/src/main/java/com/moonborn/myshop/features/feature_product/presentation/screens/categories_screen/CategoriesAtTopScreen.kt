package com.moonborn.myshop.features.feature_product.presentation.screens.categories_screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.moonborn.myshop.R
import com.moonborn.myshop.features.feature_product.domain.models.Category
import com.moonborn.myshop.features.feature_product.presentation.screens.main_screen.productList
import com.moonborn.myshop.ui.theme.BGColor
import com.moonborn.myshop.ui.theme.ColorAccent
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    scrollBehaviour: TopAppBarScrollBehavior,
    navController: NavHostController
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
                    Icon(painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "profile button",
                        tint = ColorAccent,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterStart)
                            .clickable { navController.popBackStack() }
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




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesAtTopScreen( navController: NavHostController){
    val scrollState = rememberScrollState()
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)

    val thisCoroutineScope = rememberCoroutineScope()

    var openCategory: Int? = null

    var pullDown = remember {
        mutableStateListOf<Boolean>()
    }

    for (i in categoryItemsList){
        pullDown.add(false)
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopBar(scrollBehavior, navController)
            }
        },
    ) { padding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .verticalScroll(scrollState)) {
            for (category in categoryItemsList){
                CategoriesAtTopModel(category)
            }
            categoryItemsList.forEachIndexed { index, category ->

                val angle by animateFloatAsState(
                    targetValue = if (pullDown[index]) 0f else 180f,
                    animationSpec = tween(
                        durationMillis = 100,
                        easing = LinearEasing)
                )

                Column(modifier = Modifier.fillMaxWidth()) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .background(Color.White)
                            .padding(top = 8.dp, bottom = 8.dp, end = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.Start) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_categories_at_top),
                                contentDescription = "category",
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Text(
                                text = category.name,
                                color = ColorAccent,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_downward),
                            tint = Color.Gray,
                            contentDescription = "pull down arrow",
                            modifier = Modifier
                                .size(20.dp)
                                .rotate(angle)
                                .clickable {
//                                    thisCoroutineScope.launch {
                                        if (openCategory != null && openCategory != index){
                                            pullDown[openCategory!!] = !pullDown[openCategory!!]
                                        }
                                        pullDown[index] = !pullDown[index]
                                        if (pullDown[index]) {
                                            openCategory = index
                                        }
//                                    }
                                }
                        )
                    }
                    AnimatedVisibility(
                        visible = pullDown[index],
                        enter = slideInVertically(
                            tween(100)
                        ),
                        exit = slideOutVertically(
                            tween(100)
                        )
                    ) {
                        Column() {
                            Text(
                                text = "See All",
                                color = ColorAccent,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp, vertical = 10.dp)
                                    .background(
                                        BGColor
                                    ))
                            Column(){
                                for (sCategory in category.childCategories) {
                                    Text(text = sCategory.name,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(BGColor)
                                            .padding(horizontal = 10.dp, vertical = 10.dp),
                                        fontWeight = FontWeight.Normal, color = ColorAccent)
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}


val subCategory = mutableListOf<Category>()

fun subCategoryFun(){
    for (i in 1.. 20){
        subCategory.add(
            Category(
                "${i}Category",
                mockCategories().random().name,
                productsList = productList(),
                childCategories = mutableListOf()
            )
        )
//        for (i1 in categoryItemsList){
//            if (i1.name == subCategory.get(subCategory.size - 1).parentCategory){
//                i1.childCategories.add(subCategory.get(subCategory.size - 1))
//                Log.i("categoryItemChildren", "${i1.childCategories}")
//            }
//        }

        categoryItemsList.find { subCategory.get(subCategory.size - 1).parentCategory == it.name }?.childCategories?.add(
            subCategory.get(subCategory.size - 1))
    }
    for (i in categoryItemsList){
        Log.i("categoryItemChildren", "${i.name} + ${i.childCategories}")
    }
}

@Composable
fun CategoriesAtTopModel(category: Category){




}