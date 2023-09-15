package com.moonborn.myshop.features.feature_product.presentation.products.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moonborn.myshop.R
import com.moonborn.myshop.ui.theme.MyShopTheme

@Composable
fun CategoryModel(
//    painter: Painter,
    contentDescription: String,
    name: String,
    modifier: Modifier = Modifier
){

    Card(
        modifier = modifier
            .height(140.dp)
            .width(160.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Image(painter = painterResource(id = R.drawable.cold_tea),
                contentDescription = contentDescription,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
                    .clip(shape = RoundedCornerShape(10.dp)))
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White,
                            Color.Transparent
                        ),
                        startY = 0f,
                        endY = 280f
                    )
                )
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.TopCenter
            ){
                Text(name, style = TextStyle(color = Color.Black, fontSize = 15.sp))
            }

        }
    }
}

//Lexend font family would fir well


@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    MyShopTheme {
        CategoryModel(contentDescription = "category", name = "category")
    }

}