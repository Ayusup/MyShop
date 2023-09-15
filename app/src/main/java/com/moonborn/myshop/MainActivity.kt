package com.moonborn.myshop

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.moonborn.myshop.ui.BottomMenuContent
import com.moonborn.myshop.features.feature_product.presentation.util.navigation.ScreensRoutes
import com.moonborn.myshop.features.feature_product.presentation.screens.cart_screen.CartScreen
import com.moonborn.myshop.features.feature_product.presentation.screens.categories_screen.CategoriesScreenNavigation
import com.moonborn.myshop.features.feature_product.presentation.screens.favourites_screen.FavouritesScreen
import com.moonborn.myshop.features.feature_product.presentation.screens.main_screen.BottomMenuItem
import com.moonborn.myshop.features.feature_product.presentation.screens.main_screen.MainScreenNavigation
import com.moonborn.myshop.features.feature_planned_lists.presentation.PlannedListScreen
import com.moonborn.myshop.features.feature_product.presentation.products.ProductsViewModel
import com.moonborn.myshop.ui.screens.profile_screen.ProfileScreen
import com.moonborn.myshop.ui.theme.MyShopTheme
import com.moonborn.myshop.ui.util.ClearRippleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyShopTheme {

                val viewModel = hiltViewModel<ProductsViewModel>()

                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }

                val navController = rememberNavController()
                CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
                    ProfileScreen(navController)
                }

//                Box(modifier = Modifier
//                    .fillMaxWidth(.5f)
//                    .padding(16.dp)) {
//                    CategoryModel(contentDescription = "category", name = "category")
//                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(parentPadding: PaddingValues, function: () -> Unit){
    val pagerState  = rememberPagerState(pageCount = 5, initialOffscreenLimit = 5)
//    val HomeScreens = listOf(
//        MainScreen(parentPadding = PaddingValues(), navController = NavHostController())
//    )
    val tabItems = listOf(
        BottomMenuContent("Home", ScreensRoutes.MainScreenRootNav.route, R.drawable.home_selected, R.drawable.home_unselected),
        BottomMenuContent("Categories", ScreensRoutes.Categories.route, R.drawable.categories_selected, R.drawable.categories_unselected),
        BottomMenuContent("Cart", ScreensRoutes.Cart.route, R.drawable.cart_selected, R.drawable.cart_unselected),
        BottomMenuContent("Favourites", ScreensRoutes.Favourites.route, R.drawable.heart_selected, R.drawable.heart_unselected),
        BottomMenuContent("Planned", ScreensRoutes.PlannedList.route, R.drawable.calendar_selected, R.drawable.calendar_unselected)
    )

    val MCoroutineScope = rememberCoroutineScope()
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        HorizontalPager(
            modifier = Modifier.weight(9f),
            state = pagerState,
            dragEnabled = false
        ) { page ->
            when(page){
                0 -> MainScreenNavigation(function)
                1 -> CategoriesScreenNavigation(function)
                2 -> CartScreen(parentPadding = parentPadding, function = function)
                3 -> FavouritesScreen(parentPadding = parentPadding, function = function)
                4 -> PlannedListScreen(parentPadding = parentPadding, function)
            }
        }

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .weight(.8f),
            indicator = {
                    tabPositions ->  TabRowDefaults.Indicator(
                modifier = Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .width(10.dp)
                    .height(2.dp),
//                height = 2.dp,
//                color = ColorAccent
            )
            }
        ) {
            tabItems.forEachIndexed { index, bottomMenuContent ->
                BottomMenuItem(
                    item = tabItems.get(index),
                    pagerState.currentPage == index,
                    onClick = {
                        MCoroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }

    }

}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyShopTheme {
        Greeting("Android")
    }
}



@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T{
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this){
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}


