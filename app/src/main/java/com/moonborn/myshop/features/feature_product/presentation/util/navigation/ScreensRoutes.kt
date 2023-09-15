package com.moonborn.myshop.features.feature_product.presentation.util.navigation

sealed class ScreensRoutes(val route: String){
    object Root: ScreensRoutes(route = "root")
    object MainScreenRootNav: ScreensRoutes(route = "main_screen_root_nav")
    object CategoriesScreenRootNav: ScreensRoutes(route = "categories_screen_root_nav")
    object CartScreenRootNav: ScreensRoutes(route = "cart_screen_root_nav")
    object FavouritesScreenRootNav: ScreensRoutes(route = "favourites_screen_root_nav")
    object PlannedListScreenRootNav: ScreensRoutes(route = "planned_screen_root_nav")
    object Main: ScreensRoutes(route = "main_screen")
    object PlannedList: ScreensRoutes(route = "planned_lists_screen")
    object Categories: ScreensRoutes(route = "categories_screen")
    object Cart: ScreensRoutes(route = "cart_screen")
    object SeeAllScreen: ScreensRoutes(route = "see_all_screen")
    object Favourites: ScreensRoutes(route = "favourites_screen")
    object CategoriesAtTop: ScreensRoutes(route = "categories_at_top")
    object Brands: ScreensRoutes(route = "brands_screen")
    object Search: ScreensRoutes(route = "search_screen")
    object ProductScreen: ScreensRoutes(route = "product_screen")
    object ProductScreenArgument: ScreensRoutes(route = "product_id")
}
