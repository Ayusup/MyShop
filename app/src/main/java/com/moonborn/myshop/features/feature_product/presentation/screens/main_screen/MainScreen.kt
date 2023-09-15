package com.moonborn.myshop.features.feature_product.presentation.screens.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.moonborn.myshop.R
import com.moonborn.myshop.features.feature_ads.domain.model.AdsModel
import com.moonborn.myshop.features.feature_product.domain.models.Product
import com.moonborn.myshop.features.feature_product.presentation.products.components.ProductInDealsModel
import com.moonborn.myshop.ui.BottomMenuContent
import com.moonborn.myshop.features.feature_product.presentation.util.navigation.ScreensRoutes
import com.moonborn.myshop.ui.theme.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layoutId
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.moonborn.myshop.features.feature_product.presentation.products.ProductScreen
import com.moonborn.myshop.features.feature_product.presentation.screens.brands_screen.BrandsScreen
import com.moonborn.myshop.features.feature_product.presentation.screens.categories_screen.CategoriesAtTopScreen
import com.moonborn.myshop.features.feature_product.presentation.screens.categories_screen.mockCategories
import com.moonborn.myshop.features.feature_product.presentation.screens.categories_screen.subCategoryFun

private val tempAdsList = listOf<AdsModel>(AdsModel("1", "sd", "sd"), AdsModel("2", "sd", "sd"), AdsModel("3", "sd", "sd"))


fun productList(): List<Product> {
    val tempProductsList = mutableListOf<Product>()
    for (i in 1..20){
        tempProductsList.add(
            Product("1234$i", "Cold Tea", "new", "das", "", 45.00, true, false, false, false, 10000, amountInCart = 0))
    }
    return tempProductsList
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreenTopBar(
    scrollBehaviour: TopAppBarScrollBehavior,
    function: () -> Unit,
    navController: NavHostController
){
    TopAppBar(
        title = {

        },
        scrollBehavior = scrollBehaviour,
        modifier = Modifier
            .fillMaxWidth()
            .layoutId("main"),
        navigationIcon = {},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(horizontal = 10.dp),


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
                    Image(painter = painterResource(id = R.drawable.walmart_lgo),
                        contentDescription = "Walmart",
                        modifier = Modifier
                            .fillMaxHeight()
                            .align(Alignment.Center))
                    Row(modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterEnd)
                        .fillMaxWidth(.2f),
                        horizontalArrangement = Arrangement.SpaceAround){

                        Icon(painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "search button",
                            tint = ColorAccent,
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically)
                                .clickable { navController.navigate(ScreensRoutes.Search.route) }
                        )
                        Icon(painter = painterResource(id = R.drawable.ic_phone),
                            contentDescription = "call button",

                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically)
                        )

                    }

                }
            }

        }

    )


}



@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun AdsCarousel(ads: List<AdsModel>){

//    val pagerState = rememberPagerState(
//        initialPage = 0,
//        initialPageOffsetFraction = 0f
//    ) {
//        tempAdsList.size
//    }

    val pagerState: PagerState = com.google.accompanist.pager.rememberPagerState(
        pageCount = tempAdsList.size,
        initialPage = 0,
        infiniteLoop = true
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(BGColor),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top){
        com.google.accompanist.pager.HorizontalPager(
            modifier = Modifier.height(200.dp),
            state = pagerState) { index ->

            val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffset
            val imageSize by animateFloatAsState(
                targetValue = if(pageOffset != 0.0f) 0.96f else 1f,
                animationSpec = tween(300)
            )
//            val matrix = remember{
//                ColorMatrix()
//            }
//
//            LaunchedEffect(key1 = imageSize){
//                if(pageOffset != 0.0f){
//                    matrix.setToSaturation(0f)
//                } else {
//                    matrix.setToSaturation(1f)
//                }
//            }

            Image(painter = painterResource(id = R.drawable.walmart),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {

                    }
                    .graphicsLayer {
                        scaleX = imageSize
                        scaleY = imageSize
                    },
//                colorFilter = ColorFilter.colorMatrix(matrix)
            )
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                .align(Alignment.CenterHorizontally)
                .background(BGColor),
            activeColor = ColorAccent,
            inactiveColor = Color.LightGray,
            indicatorWidth = 10.dp,
            pagerState = pagerState)
    }
}

@Composable
fun DealNameSeeAllBtns(
    dealName: String,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    
    Row(modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = dealName, fontSize = 18.sp, color = Color.Black, modifier = modifier.padding(8.dp), fontFamily = Gilroy, fontWeight = FontWeight.Bold)
        Text(text = "See all", fontSize = 16.sp, color = ColorAccent, fontFamily = Gilroy, fontWeight = FontWeight.Medium, modifier = modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(ScreensRoutes.SeeAllScreen.route)
            })
    }
    
}

@Composable
fun DealsSection(dealName: String,
                 products: List<Product>,
                 navController: NavHostController,
                 modifier: Modifier = Modifier)
{
    LazyRow(modifier = modifier
        .fillMaxWidth()
        .padding(start = 6.dp, end = 6.dp, top = 8.dp, bottom = 8.dp)){
        items(products.size){ index ->
            val product = productList().get(index)
            ProductInDealsModel(product = product, navHostController = navController)
        }
    }
}





@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    navController: NavController,
    activeHighlightColor: Color = ColorAccent,
    activeTextColor: Color = ColorAccent,
    inactiveTextColor: Color = Color.Black,
    initialSelectedItemIndex: Int = 0,
    onItemClick: (BottomMenuContent) -> Unit
){
    var selectedItemIndex by remember {
       mutableStateOf(initialSelectedItemIndex)
    }
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = Alignment.CenterVertically,
        backgroundColor = Color.White,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ){
        items.forEachIndexed{ index, item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomMenuItem(
                item = item,
//                isSelected = index == selectedItemIndex &&
                        isSelected = selected,
                activeTextColor = ColorAccent,
                inactiveTextColor = Color.Black,
                onClick = { onItemClick(item) }
            )
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ColorAccent,
    activeTextColor: Color = ColorAccent,
    inactiveTextColor: Color = Color.Black,
    onClick: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(horizontal = 6.dp)
    ) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
        ) {
            //Add Badger
          Image(
              painter = if(isSelected) painterResource(id = item.iconSelectedId) else  painterResource(id = item.iconUnselectedId),
              contentDescription = item.title,
//              tint = if(isSelected) activeTextColor else inactiveTextColor,
              modifier = Modifier.size(28.dp)
          )
        }
        Text(
            text = item.title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Gilroy,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
//        Box(modifier = Modifier
//            .width(20.dp)
//            .height(4.dp)
//            .align(Alignment.CenterHorizontally)
//            .clip(shape = RoundedCornerShape(2.dp))){
//            androidx.compose.animation.AnimatedVisibility(
//                visible = isSelected,
//                enter = fadeIn(tween(200)),
//                exit = fadeOut(tween(200))){
//                Box(modifier = Modifier
//                    .width(20.dp)
//                    .height(3.dp)
//                    .align(Alignment.BottomCenter)
//                    .background(ColorAccent)
//                    .clip(shape = RoundedCornerShape(2.dp)))
//            }
//        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreenNavigation(
    function: () -> Unit
){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreensRoutes.Main.route, route = ScreensRoutes.MainScreenRootNav.route){

        composable(ScreensRoutes.Main.route){
            MainScreen( navController, function)
        }
        composable(ScreensRoutes.SeeAllScreen.route){
            SeeAllScreen(navController)
        }
        composable(ScreensRoutes.CategoriesAtTop.route){
            CategoriesAtTopScreen( navController)
        }
        composable(ScreensRoutes.Brands.route){
            BrandsScreen(navController)
        }
        composable(ScreensRoutes.Search.route){
            SearchScreen(navController)
        }
        composable(
            "${ScreensRoutes.ProductScreen.route}/{${ScreensRoutes.ProductScreenArgument.route}}",
            arguments = listOf(navArgument(ScreensRoutes.ProductScreenArgument.route) { type = NavType.StringType } )
            ){ backStackEntry ->
            ProductScreen(navController, backStackEntry.arguments?.getString(ScreensRoutes.ProductScreenArgument.route))

        }

    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    function: () -> Unit
) {
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)

    val scrollState = rememberScrollState()

    mockCategories()
    subCategoryFun()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                MainScreenTopBar(scrollBehavior, function, navController)
            }
        },
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .background(BGColor)
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(scrollState),
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                    Row(
                        modifier = Modifier.clickable { navController.navigate(ScreensRoutes.CategoriesAtTop.route) },
                        verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier
                                .size(28.dp)
                                .padding(horizontal = 4.dp),
                            painter = painterResource(id = R.drawable.ic_categories_at_top),
                            contentDescription = "Categories"
                        )
                        Text(text = "Categories",
                            fontSize = 16.sp,
                            color = ColorAccent,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Gilroy)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .size(28.dp)
                                .padding(horizontal = 4.dp)
                                .clickable { navController.navigate(ScreensRoutes.Brands.route) },
                            painter = painterResource(id = R.drawable.ic_brands),
                            tint = ColorAccent,
                            contentDescription = "Brands"
                        )
                        Text(
                            text = "Brands",
                            fontSize = 16.sp,
                            color = ColorAccent,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Gilroy
                        )
                    }
                }
                AdsCarousel(ads = tempAdsList)
                Spacer(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                )
                DealNameSeeAllBtns(dealName = "New Products", navController)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
                DealsSection("New Products", productList(), navController)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
                DealNameSeeAllBtns(dealName = "Sales and Promotions", navController)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
                DealsSection("Sales and Promotions", productList(), navController)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
                DealNameSeeAllBtns(dealName = "Popular products", navController)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
                DealsSection("Popular products", productList(), navController)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
                DealNameSeeAllBtns(dealName = "Sales", navController)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
                DealsSection("Sales", productList(), navController)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )


            }


        }
    }
}
