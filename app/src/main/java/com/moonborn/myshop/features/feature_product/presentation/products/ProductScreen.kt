package com.moonborn.myshop.features.feature_product.presentation.products

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.moonborn.myshop.R
import com.moonborn.myshop.features.feature_product.domain.models.Product
import com.moonborn.myshop.features.feature_product.presentation.screens.main_screen.productList
import com.moonborn.myshop.ui.theme.BGColor
import com.moonborn.myshop.ui.theme.ColorAccent
import com.moonborn.myshop.ui.theme.Gilroy
import com.moonborn.myshop.ui.theme.Green
import com.moonborn.myshop.ui.theme.MRed


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductScreen(navigation: NavHostController, productId: String?){

    val product: Product? = productList().find { it.id == productId }

    val piecesState = remember {
        mutableIntStateOf(value = product?.amountInCart ?: 0)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(BGColor)
    ) {
        Row(modifier = Modifier
            .weight(.1f)
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navigation.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = ColorAccent,
                    modifier = Modifier
                        .size(28.dp)
                )
            }
            Row(modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceBetween) {

                Box(modifier = Modifier.padding(horizontal = 10.dp)){
                    Icon(painter = painterResource(id = R.drawable.calendar_unselected),
                        contentDescription = "add to favourites button",
                        tint = MRed,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { }
                    )
                }


                var isSelected by remember {
                    mutableStateOf(false)
                }

                Box(modifier = Modifier.padding(horizontal = 10.dp)){
                    AnimatedContent(targetState = isSelected,
                        modifier = Modifier
                            .clickable {
                                isSelected = !isSelected
                            }
                            .size(32.dp),
                        content = {Selected ->
                            if(Selected){
                                Icon(painter = painterResource(id = R.drawable.heart_filled),
                                    contentDescription = "add to favourites button",
                                    tint = MRed,
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                            } else {
                                Icon(painter = painterResource(id = R.drawable.heart_unselected),
                                    contentDescription = "add to favourites button",
                                    tint = MRed,
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                            }
                        },
                        transitionSpec = {
                            expandIn(
                                expandFrom = Alignment.Center
                            ) with fadeOut(
                                animationSpec = tween(1)
                            )
                        }
                    )
                }
            }
        }
        Box(modifier = Modifier
            .weight(.4f)
            .fillMaxWidth()
            .background(Color.White)){
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.walmart_lgo),
                contentDescription = "Product Image",

                )
        }

        Column(
            modifier = Modifier
                .weight(.7f)
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = product?.name ?: "",
                fontFamily = Gilroy,
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                color = ColorAccent
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = "Article: ${product?.id ?: 0}",
                fontFamily = Gilroy,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Row(modifier = Modifier.padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier
                ) {

                    Text(
                        text = "price for pc",
                        fontSize = 18.sp,
                        color = Color.Gray,
                        modifier = Modifier.alpha(.7f)
                    )
                    Text(
                        text = "${product?.amountInCart ?: 0.00} TMT",
                        fontWeight = FontWeight.Medium,
                        color = ColorAccent,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                    )
                }

                Box(modifier = Modifier.fillMaxWidth()) {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = piecesState.value < 1,
                        enter = fadeIn(tween(300)),
                        exit = fadeOut(tween(300))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .align(Alignment.BottomEnd)
                        ) {
                            TextButton(
                                onClick = { piecesState.value++ },
                                modifier = Modifier
                                    .width(132.dp)
                                    .height(48.dp)
                                    .align(Alignment.BottomEnd),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Green)
                            ) {
                                Text(text = "To Cart", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                            }
                        }
                    }


                    androidx.compose.animation.AnimatedVisibility(
                        visible = piecesState.value > 0,
                        enter = fadeIn(tween(200)),
                        exit = fadeOut(tween(200))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .align(Alignment.BottomEnd)
                        ) {
                            Row(modifier = Modifier.align(Alignment.CenterEnd)) {

                                IconButton(
                                    onClick = {
                                        if (piecesState.value > 0) {
                                            piecesState.value--
                                        }
                                    },
//                        shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp),
                                    modifier = Modifier
                                        .clip(
                                            shape = RoundedCornerShape(
                                                10.dp,
                                                0.dp,
                                                0.dp,
                                                10.dp
                                            )
                                        )
                                        .background(ColorAccent)
                                        .height(48.dp)
                                        .width(40.dp)
                                ) {


                                    Icon(
                                        painter = painterResource(id = R.drawable.minus),
                                        contentDescription = "add to cart button",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .size(12.dp)
                                            .background(Color.Transparent)
                                    )
                                }
                                Card(
                                    modifier = Modifier
                                        .height(48.dp)
                                        .width(52.dp),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 1.dp
                                    ),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White,
                                    ),
                                    shape = RoundedCornerShape(0.dp)
                                ) {
                                    Box(modifier = Modifier.fillMaxSize()) {
                                        Text(
                                            modifier = Modifier.align(Alignment.Center),
                                            fontWeight = FontWeight.SemiBold,
                                            text = "${piecesState.value} pc",
                                            fontSize = 11.sp
                                        )
                                    }
                                }
                                IconButton(
                                    onClick = { piecesState.value++ },
                                    modifier = Modifier
                                        .clip(
                                            shape = RoundedCornerShape(
                                                0.dp,
                                                10.dp,
                                                10.dp,
                                                0.dp
                                            )
                                        )
                                        .background(Green)
                                        .height(48.dp)
                                        .width(40.dp)
                                        .indication(
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() })
                                ) {


                                    Icon(
                                        painter = painterResource(id = R.drawable.plus),
                                        contentDescription = "add to cart button",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .size(12.dp)
                                            .background(Color.Transparent)
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }

}