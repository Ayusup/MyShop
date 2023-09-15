package com.moonborn.myshop.features.feature_product.presentation.products.components

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.moonborn.myshop.R
import com.moonborn.myshop.features.feature_product.domain.models.Product
import com.moonborn.myshop.features.feature_product.presentation.util.navigation.ScreensRoutes
import com.moonborn.myshop.features.feature_product.presentation.util.shadow
import com.moonborn.myshop.ui.theme.*
import com.moonborn.myshop.ui.util.ClearRippleTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductInDealsModel(
//    painter: Painter,
//    contentDescription: String,
    product: Product?,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
){
    CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
        Box(modifier = modifier
            .padding(horizontal = .7.dp, vertical = 2.dp)
            .clip(shape = RoundedCornerShape(14.dp, 14.dp, 14.dp, 14.dp))
        ){
            Box(modifier = Modifier
                .shadow(
                    color = ShadowColor,
                    offsetY = (0).dp,
                    offsetX = (0).dp,
                    blurRadius = 40.dp,
                    spread = 1.dp//Spread of the solid color, as small you make as visible is your blur
                )
                .clip(shape = RoundedCornerShape(14.dp, 14.dp, 14.dp, 14.dp))
            ) {
                Card(
                    modifier = Modifier
                        .height(280.dp)
                        .width(190.dp)
                        .padding(end = 5.dp, start = 5.dp, top = 6.dp, bottom = 6.dp)
                        .clickable {

                            if (product != null) {
                                navHostController.navigate("${ScreensRoutes.ProductScreen.route}/${product.id}")
                            }

                        },
                    shape = RoundedCornerShape(14.dp),
//                elevation = CardDefaults.cardElevation(
//                    defaultElevation = 4.dp
//                ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),

                    ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.45f)
                            .padding(6.dp, 12.dp)
                    ) {
                        Image(
                            painterResource(id = R.drawable.cold_tea),
                            contentDescription = "Product",
                            modifier = Modifier
                                .fillMaxWidth(.8f)
                                .fillMaxHeight()
                                .align(Alignment.TopCenter),
                            contentScale = ContentScale.FillBounds
                        )

                        var isSelected by remember {
                            mutableStateOf(false)
                        }

                        val scale = remember{
                            androidx.compose.animation.core.Animatable(0f)
                        }


                        val overShootInterpolator = remember{
                            OvershootInterpolator(2.5f)
                        }

                        val scope = rememberCoroutineScope()

//                        LaunchedEffect(key1 = !isSelected){
//                            scale.animateTo(
//                                targetValue = 1f,
//                                animationSpec = tween(
//                                    durationMillis = 400,
//                                    easing = {
//                                        overShootInterpolator.getInterpolation(it)
//                                    }
//                                )
//                            )
//                        }



                        IconButton(
                            onClick = {
                                isSelected = !isSelected
                                  if (isSelected){
                                      scope.launch {
                                          scale.animateTo(
                                              targetValue = 1f,
                                              animationSpec = tween(
                                                  durationMillis = 150,
                                                  easing = {
                                                      overShootInterpolator.getInterpolation(it)
                                                  }
                                              )
                                          )
                                      }
                                  }else{

                                          scope.launch {
                                              scale.animateTo(
                                                  targetValue = 0f,
                                                  animationSpec = tween(
                                                      durationMillis = 150,
//                                                          easing = {
//                                                              overShootInterpolator.getInterpolation(it)
//                                                          }
                                                  )
                                              )
                                          }
                                  }
                              },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.heart_unselected),
                                contentDescription = "add to favourites button",
                                tint = MRed,
                                modifier = Modifier
                                    .size(28.dp)
                                    .align(Alignment.Center)
                                    .padding(2.dp)
                            )
                            Icon(painter = painterResource(id = R.drawable.heart_filled),
                                contentDescription = "add to favourites button",
                                tint = MRed,
                                modifier = Modifier
                                    .size(28.dp)
                                    .align(Alignment.Center)
                                    .scale(scale.value)
                                    .padding(2.dp)
                            )

                        }
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.4f)
                        .padding(6.dp, 0.dp),){
                        Text(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(.7f),
                            text = product?.name ?: "",
                            maxLines = 3,
                            color = Color.DarkGray,
                            style = MTextStyle.bodyMedium
                        )
                        Icon(painter = painterResource(id = R.drawable.calendar_unselected),
                            contentDescription = "add to favourites button",
                            tint = MRed,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable { }
                                .align(Alignment.TopEnd)
                        )
                    }

                    var isVisible by remember {
                        mutableStateOf(true)
                    }


                    AnimatedVisibility(visible = !isVisible,
                        enter = fadeIn(tween(200)),
                        exit = fadeOut(tween(200))) {
                        val piecesState = remember {
                            mutableIntStateOf(value = 1)
                        }

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 6.dp, bottom = 6.dp, start = 6.dp, end = 0.dp)){

                            Box(modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(.7f)
                                .padding(top = 4.dp)
                                .align(Alignment.TopStart)){

                                Box(
                                    modifier = Modifier

                                        .border(
                                            width = 1.dp,
                                            color = Green,
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                ) {
                                    Text(
                                        text = "${piecesState.value} pc",
                                        style = MTextStyle.bodySmall,
                                        color = ColorAccent,
                                        fontSize = 12.sp,
                                        modifier = Modifier
                                            .align(Alignment.TopStart)
                                            .padding(6.dp)
                                    )
                                }
                                Text(
                                    text = "${piecesState.value * (product?.price ?: 0.00)} TMT",
                                    style = MTextStyle.bodyMedium,
                                    color = ColorAccent,
                                    fontSize = 18.sp,
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(bottom = 6.dp))


                            }

                            Column(modifier = Modifier
                                .align(Alignment.CenterEnd)){

                                IconButton(
                                    onClick = { piecesState.value ++ },
                                    modifier = Modifier
                                        .clip(shape = RoundedCornerShape(8.dp, 0.dp, 0.dp, 0.dp))
                                        .background(Green)
                                        .height(34.dp)
                                        .width(34.dp)
//                                        .indication(
//                                            indication = null,
//                                            interactionSource = remember { MutableInteractionSource() })

                                ) {


                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "add to cart button",
                                        tint = Color.White,


                                        modifier = Modifier
                                            .size(14.dp)
                                            .background(Color.Transparent)

                                    )
                                }
                                IconButton(
                                    onClick = { if (piecesState.value > 1) {piecesState.value --} else {isVisible = !isVisible} },
//                        shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp),
                                    modifier = Modifier
                                        .clip(shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 8.dp))
                                        .background(ColorAccent)
                                        .height(34.dp)
                                        .width(34.dp)
                                ) {


                                    Icon(
                                        imageVector = Icons.Default.Remove,
                                        contentDescription = "add to cart button",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .size(14.dp)
                                            .background(Color.Transparent)
                                    )
                                }


                            }


                        }
                    }

                    AnimatedVisibility(visible = isVisible,
                        enter = fadeIn(tween(200)),
                        exit = fadeOut(tween(200))
                    ) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 6.dp, bottom = 16.dp, start = 6.dp, end = 0.dp)){

                            Box(modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(.7f)
                                .align(Alignment.BottomStart)){

                                Text(
                                    text = "price for pc",
                                    style = MTextStyle.bodySmall,
                                    color = Color.LightGray,
                                    fontSize = 11.sp,
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(bottom = 20.dp))
                                Text(
                                    text = "${product?.price ?: 0.00} TMT",
                                    style = MTextStyle.bodyMedium,
                                    color = ColorAccent,
                                    fontSize = 18.sp,
                                    modifier = Modifier
                                        .align(Alignment.BottomStart))


                            }

                            IconButton(
                                onClick = { isVisible = false },
//                        shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp),
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(8.dp, 0.dp, 0.dp, 8.dp))
                                    .background(BtnBg)
                                    .height(46.dp)
                                    .width(42.dp)
                                    .align(Alignment.BottomEnd)

                            ) {


                                Icon(
                                    painter = painterResource(id = R.drawable.cart_unselected),
                                    contentDescription = "add to cart button",
                                    tint = IcCartGreen,


                                    modifier = Modifier
                                        .size(22.dp)
                                        .align(Alignment.Center)
                                        .clip(RoundedCornerShape(20.dp, 0.dp, 0.dp, 20.dp))
                                        .align(Alignment.Center)

                                )
                            }

                        }
                    }


                }
            }
        }
    }



}
