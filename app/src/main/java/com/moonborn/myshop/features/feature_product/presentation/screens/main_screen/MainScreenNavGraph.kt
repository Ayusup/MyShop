package com.moonborn.myshop.features.feature_product.presentation.screens.main_screen

//@OptIn(ExperimentalMaterialApi::class)
//fun NavGraphBuilder.mainScreenNavGraph(
//    navController: NavHostController,
//    parentPadding: PaddingValues,
//    function: () -> Unit
//){
//    navigation(
//        startDestination = ScreensRoutes.Main.route,
//        route = ScreensRoutes.MainScreenRootNav.route){
//        composable(ScreensRoutes.Main.route){
//            MainScreen(parentPadding, navController, function)
//        }
//        composable(ScreensRoutes.SeeAllScreen.route){
//            SeeAllScreen(parentPadding)
//        }
//        composable(ScreensRoutes.CategoriesAtTop.route){
//            CategoriesAtTopScreen(parentPadding = parentPadding, navController)
//        }
//        composable(ScreensRoutes.Brands.route){
//            BrandsScreen(parentPadding = parentPadding, navController)
//        }
//    }
//}