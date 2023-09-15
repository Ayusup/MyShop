package com.moonborn.myshop.features.feature_product.presentation.util

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.shadow(
    color: Color = Color.Black,
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp
) = then(
    drawBehind {
        drawIntoCanvas {canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            if (blurRadius != 0.dp){
                frameworkPaint.maskFilter = (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }
            frameworkPaint.color = color.toArgb()


            val leftPixel = (0f - spread.toPx()) + offsetX.toPx()
            val topPixel = (0f - spread.toPx()) + offsetY.toPx()
            val rightPixel = size.width + spread.toPx()
            val bottomPixel = size.height + spread.toPx()
            canvas.drawRect(
                left = leftPixel,
                right = rightPixel,
                top = topPixel,
                bottom = bottomPixel,
                paint = paint
            )
        }
    }
)