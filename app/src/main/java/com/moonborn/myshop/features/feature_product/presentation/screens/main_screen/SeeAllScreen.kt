package com.moonborn.myshop.features.feature_product.presentation.screens.main_screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.moonborn.myshop.R
import com.moonborn.myshop.features.feature_product.presentation.products.components.ProductInDealsModel
import com.moonborn.myshop.ui.theme.BGColor
import com.moonborn.myshop.ui.theme.ColorAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SeeAllTopBar(
    scrollBehaviour: TopAppBarScrollBehavior
){
    TopAppBar(
        title = {
            Text(
                modifier = Modifier,
                text = "See All",
                fontSize = 20.sp,
                color = ColorAccent
            )
        },
//        scrollBehavior = scrollBehaviour,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        navigationIcon = {

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "profile button",
                    tint = ColorAccent,
                    modifier = Modifier
                        .size(24.dp)

                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        actions = {

        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopSearchBar(scrollBehaviour: TopAppBarScrollBehavior){
    TopAppBar(
        title = {
        },
        scrollBehavior = scrollBehaviour,
        modifier = Modifier
            .fillMaxWidth()
            .background(BGColor),

        navigationIcon = {

        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = BGColor),
        actions = {
            Surface(modifier = Modifier
                .background(BGColor)
                .fillMaxHeight()
                .fillMaxWidth()) {
                var text by remember {
                    mutableStateOf("What would you like to buy")
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .background(BGColor)
                ) {
                    Surface(
                        modifier = Modifier

                            .padding(start = 10.dp)
                            .fillMaxWidth(.8f)
                            .clip(shape = RoundedCornerShape(8.dp)),
                        shadowElevation = 10.dp
                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
//                                .height(48.dp)
                                .background(
                                    Color.White,
                                    MaterialTheme.shapes.large,
                                ),
                            shape = RoundedCornerShape(10.dp),
                            value = text,
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 16.sp,
                                textAlign = TextAlign.Start
                            ),
                            onValueChange = { typedText ->
                                text = typedText
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                cursorColor = Color.Black,
                                disabledLabelColor = Color.LightGray,
                                unfocusedLabelColor = Color.LightGray,
                                focusedLabelColor = ColorAccent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            singleLine = true,
                            leadingIcon = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        modifier = Modifier.size(24.dp),
                                        contentDescription = "Search icon"
                                    )
                                }
                            },
//                    trailingIcon = {
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_search),
//                                modifier = Modifier.size(24.dp),
//                                contentDescription = "Search icon"
//                            )
//                        }
//                    }
                        )
                    }
                    Surface(
                        shadowElevation = 10.dp,
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(8.dp)),
                    ) {
                        IconButton(
                            modifier = Modifier
                                .size(42.dp)
                                .background(ColorAccent)
                                .clip(shape = RoundedCornerShape(8.dp)),
                            onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_options),
                                modifier = Modifier.size(24.dp),
                                contentDescription = "Filter",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun SeeAllScreen(navController: NavHostController) {
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)

    val color by animateColorAsState(
        targetValue = if (appBarState.collapsedFraction < .5f) BGColor else Color.White,
        animationSpec = tween(200)
    )


    Scaffold(
        modifier = Modifier
        ,
        topBar = {
//            Surface(shadowElevation = 10.dp) {
                Column() {
                    SeeAllTopBar(scrollBehaviour = scrollBehavior)
                    TopSearchBar(scrollBehaviour = scrollBehavior)
                }


//            }
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .background(color = color)) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(16, 16, 0, 0))
                    .padding(top = 20.dp)
                    .background(Color.White)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(top = 10.dp)
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .clip(shape = RoundedCornerShape(10, 10, 0, 0)),
                    columns = GridCells.Fixed(2),

                    ) {
                    items(productList().size) { i ->
                        Box(
                            modifier = Modifier.padding(horizontal = 0.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            val product = productList().get(i)
                            ProductInDealsModel(
                                product = product,
                                navHostController = navController
                            )
                        }
                    }
                }
            }
        }
    }

}