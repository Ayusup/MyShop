package com.moonborn.myshop.features.feature_product.presentation.screens.brands_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moonborn.myshop.R
import com.moonborn.myshop.features.feature_product.domain.models.Brand
import com.moonborn.myshop.ui.theme.ColorAccent

@Composable
fun BrandsModel(brand: Brand){
    Card( 
        modifier = Modifier
            .size(110.dp)
            .padding(10.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White
    ) {
        Column( 
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(56.dp)
                    .padding(top = 2.dp),
                painter = painterResource(id = R.drawable.walmart_lgo), 
                contentDescription = brand.name)
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 2.dp),
                text = brand.name,
                fontWeight = FontWeight.Normal,
                color = ColorAccent,
                fontSize = 12.sp,
                maxLines = 1
            )
        }
    }
}

@Composable
@Preview
fun BrandModeLPreview(){
    Box(modifier = Modifier.size(120.dp)) {
//        BrandsModel()
    }
}