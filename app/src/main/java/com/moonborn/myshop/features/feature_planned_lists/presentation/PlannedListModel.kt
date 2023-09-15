package com.moonborn.myshop.features.feature_planned_lists.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moonborn.myshop.R
import com.moonborn.myshop.features.feature_product.domain.models.Product
import com.moonborn.myshop.features.feature_product.presentation.util.shadow
import com.moonborn.myshop.features.feature_product.presentation.screens.main_screen.productList
import com.moonborn.myshop.ui.theme.Green
import com.moonborn.myshop.ui.theme.MRed
import com.moonborn.myshop.ui.theme.PLHGradientEnd
import com.moonborn.myshop.ui.theme.PLHGradientStart
import java.time.LocalDate
import kotlin.math.roundToInt




@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlannedListModel(
    name: String,
    products: List<Product>,
    date: LocalDate
) {
    val configuration = LocalConfiguration.current

    val ParentBoxWidth = (configuration.screenWidthDp.dp - 20.dp)

    val squareSize = 310.dp
    val swipeableState = rememberSwipeableState(1)
    val sizePx = with(LocalDensity.current) {
        (ParentBoxWidth - squareSize).toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states
//    if (swipeableState.progress.from == 0){
//
//    }
    Box( modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .swipeable(
            state = swipeableState,
            anchors = anchors,
            thresholds = { T, Any -> FractionalThreshold(0.3f) },
            orientation = Orientation.Horizontal
        )
        ) {

        Column(modifier = Modifier
            .align(Alignment.TopStart)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
            Text(text = "${date.dayOfMonth}", color = Color.Black, fontWeight = FontWeight.Normal, fontSize = 26.sp)
            Text(text = "${date.month}".lowercase(), color = Color.Black, fontWeight = FontWeight.Normal, fontSize = 16.sp)
        }

        Column(modifier = Modifier
            .align(Alignment.CenterEnd)
            .padding(10.dp)
            .height(180.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly) {

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    modifier = Modifier.size(34.dp),
                    tint = MRed,
                    contentDescription = "Delete")
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    modifier = Modifier.size(34.dp),
                    tint = Green,
                    contentDescription = "Edit")
            }
        }

        Box(modifier = Modifier
            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
            .height(200.dp)
            .width(squareSize)
            .clip(shape = RoundedCornerShape(20.dp))
            ){
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)

                .shadow(
                    color = PLHGradientEnd,
                    offsetY = (0).dp,
                    offsetX = (0).dp,
                    blurRadius = 6.dp,
                    spread = .6.dp//Spread of the solid color, as small you make as visible is your blur
                )
                .clip(shape = RoundedCornerShape(10.dp))
                .alpha(.8f)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            PLHGradientEnd,
                            PLHGradientStart
                        )
                    )
                )
            ){
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 20.dp, top = 20.dp, end = 20.dp),
                    text = "${products.size} product(s)",
                    color = Color.White,
                    maxLines = 1,
                    fontSize = 20.sp)
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp,  end = 20.dp),
                    text = name,
                    color = Color.White,
                    maxLines = 2,
                    fontSize = 28.sp)

            }
        }


    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun planned(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        val date = LocalDate.now()
        PlannedListModel(name = "Thursday", date = date, products = productList())
    }
}