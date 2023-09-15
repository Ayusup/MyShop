package com.moonborn.myshop.features.feature_product.presentation.products.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moonborn.myshop.R
import com.moonborn.myshop.features.feature_product.presentation.util.shadow
import com.moonborn.myshop.ui.theme.ColorAccent
import com.moonborn.myshop.ui.theme.Green
import com.moonborn.myshop.ui.theme.MTextStyle
import com.moonborn.myshop.ui.util.ClearRippleTheme


@Composable
fun ProductsInCart(
    name: String,
    modifier: Modifier = Modifier,
    price: Double,
){
    val piecesState = remember {
        mutableIntStateOf(value = 1)
    }
    CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
        Card( modifier = Modifier
            .padding(top = 6.dp, bottom = 6.dp)
            .height(146.dp)
            .fillMaxWidth()
            .clickable {

            },
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),

        ) {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(vertical = 10.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
            ) {
                Box(
                    modifier = modifier
                        .fillMaxHeight()
                        .fillMaxWidth(.3f)
                ) {
                    Image(
                        painterResource(id = R.drawable.walmart_lgo),
                        contentDescription = "Product",
                        modifier = Modifier
                            .fillMaxHeight(.8f)
                            .align(Alignment.Center),
                        contentScale = ContentScale.Fit
                    )
                }
                Column(
                    modifier = modifier
                        .fillMaxHeight()
                        .fillMaxWidth(.35f),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        modifier = Modifier
//                    .fillMaxWidth(),
                        ,
                        text = "Product name and characteristic",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        color = ColorAccent,
                        fontSize = 18.sp
                    )
                    Text(
                        modifier = Modifier,
                        text = "In stock",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        color = Green,
                        fontSize = 11.sp
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                        ) {

                            Text(
                                text = "price for pc",
                                fontSize = 10.sp,
                                color = Color.Gray,
                                modifier = Modifier.alpha(.7f)
                            )
                            Text(
                                text = "${price} TMT",
                                fontWeight = FontWeight.Medium,
                                color = ColorAccent,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(bottom = 6.dp)
                            )
                        }
                    }
                }
                Box(
                    modifier = modifier
                        .fillMaxHeight()
                        .fillMaxWidth(1f)
                        .padding(horizontal = 10.dp)
                ) {
                    Column() {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(6.dp),
                                text = "Delete",
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2,
                                color = Green,
                                fontSize = 13.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        ) {
                            Row(modifier = Modifier.align(Alignment.CenterEnd)) {

                                IconButton(
                                    onClick = {
                                        if (piecesState.value > 1) {
                                            piecesState.value--
                                        } else {
                                        }
                                    },
//                        shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp),
                                    modifier = Modifier
                                        .clip(shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp))
                                        .background(ColorAccent)
                                        .height(44.dp)
                                        .width(36.dp)
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
                                        .height(44.dp)
                                        .width(48.dp),
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
                                            text = "1 pc",
                                            fontSize = 11.sp
                                        )
                                    }
                                }
                                IconButton(
                                    onClick = { piecesState.value++ },
                                    modifier = Modifier
                                        .clip(shape = RoundedCornerShape(0.dp, 10.dp, 10.dp, 0.dp))
                                        .background(Green)
                                        .height(44.dp)
                                        .width(36.dp)
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
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    text = "Total",
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.alpha(.7f)
                                )
                                Text(
                                    text = "${piecesState.value * price} TMT",
                                    fontWeight = FontWeight.Bold,
                                    color = ColorAccent,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



@Preview
@Composable
fun ProductInCartPreview(){
    Box(modifier = Modifier
        .width(480.dp)
        .padding(10.dp)){
        ProductsInCart(name = "Shampoo", price = 100.00)
    }

}