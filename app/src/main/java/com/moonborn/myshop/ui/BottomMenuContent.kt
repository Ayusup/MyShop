package com.moonborn.myshop.ui

import androidx.annotation.DrawableRes

data class BottomMenuContent(
    val title: String,
    val route: String,
    @DrawableRes val iconSelectedId: Int,
    @DrawableRes val iconUnselectedId: Int

    )
