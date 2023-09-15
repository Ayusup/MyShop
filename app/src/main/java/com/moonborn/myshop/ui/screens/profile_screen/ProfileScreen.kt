package com.moonborn.myshop.ui.screens.profile_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.navigation.NavHostController
import com.moonborn.myshop.HomeScreen
import com.moonborn.myshop.R
import com.moonborn.myshop.ui.theme.ColorAccent
import com.moonborn.myshop.ui.theme.Gilroy
import com.moonborn.myshop.ui.theme.ProfileScreenBGColor

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class, ExperimentalMotionApi::class)
@Composable
fun ProfileScreen(navHostController: NavHostController){
    //MotionScene variables
    val context = LocalContext.current
    val motionScene = remember{
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }


    var animate by remember {
        mutableStateOf(false)
    }
    val animateProgress by animateFloatAsState(
        targetValue = if (animate) 1f else 0f,
        tween(250)
    )



    MotionLayout(
        modifier = Modifier,
        motionScene = MotionScene(content = motionScene),
        progress = animateProgress,

    ) {


        ProfileScreen()
        Box(modifier = Modifier
            .fillMaxSize()
            .layoutId("main_layout")
            .clip(shape = RoundedCornerShape(animateProgress * 50))

//            .offset { IntOffset(swipeAbleState.offset.value.roundToInt(), 0) }
//            .clickable { animate = !animate },

        ){
            androidx.compose.material3.Surface(shadowElevation = 20.dp) {

                HomeScreen(
                    PaddingValues()
                ) { animate = !animate }
            }
        }

    }
}


@Composable
fun ProfileScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .layoutId("profile_screen")){

        Surface(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(shape = RoundedCornerShape(14.dp)),
            elevation = 20.dp
        ) {
            Box(modifier = Modifier
                .clip(shape = RoundedCornerShape(14.dp))
                .background(Color.White)
                .fillMaxHeight(.5f)
                .fillMaxWidth(.3f)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight(.12f)
                    .fillMaxWidth()
            ){
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.BottomCenter),
                    painter = painterResource(id = R.drawable.walmart_lgo),
                    contentDescription = "Walmart Logo")
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(.65f)
                    .fillMaxHeight()
            ){
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxHeight()

                ) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 20.dp, horizontal = 6.dp)
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.TopStart)

                        ) {
                            Text(
                                text = "Hello, {name}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Gilroy,
                                maxLines = 1,
                                color = ColorAccent)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Your address",
                                fontWeight = FontWeight.Medium,
                                fontFamily = Gilroy,
                                fontSize = 14.sp,
                                maxLines = 1,
                                color = ColorAccent)
                            Text(
                                text = "Ashgabat",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                maxLines = 1,
                                color = ColorAccent)
                        }
                        IconButton(
                            modifier = Modifier
                                .size(32.dp)
                                .align(Alignment.BottomEnd),
                            onClick = { /*TODO*/ }) {
                            Icon(
                                modifier = Modifier
                                    .size(32.dp)
                                    .align(Alignment.BottomEnd),
                                tint = ColorAccent,
                                painter = painterResource(id = R.drawable.ic_edit_profile_),
                                contentDescription = "edit profile")
                        }
                    }
                    Divider(
                        modifier = Modifier.alpha(.7f),
                        color = Color.LightGray)
                    Column(
                        modifier = Modifier
                            .padding(
                                vertical = 20.dp,
                                horizontal = 2.dp
                            )
                            .fillMaxSize()
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                tint = ColorAccent,
                                painter = painterResource(id = R.drawable.person),
                                contentDescription = "Personal Information")
                            Text(
                                modifier = Modifier
                                    .alpha(.8f)
                                    .padding(horizontal = 10.dp),
                                text = "Personal information",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = Gilroy,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 2)
                        }
                        Row(
                            modifier = Modifier
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                tint = ColorAccent,
                                painter = painterResource(id = R.drawable.ic_adresses),
                                contentDescription = "My addresses")
                            Text(
                                modifier = Modifier
                                    .alpha(.8f)
                                    .padding(horizontal = 10.dp),
                                text = "My addresses",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = Gilroy,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 2)
                        }
                        Row(
                            modifier = Modifier
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                tint = ColorAccent,
                                painter = painterResource(id = R.drawable.ic_trolley),
                                contentDescription = "Orders")
                            Text(
                                modifier = Modifier
                                    .alpha(.8f)
                                    .padding(horizontal = 10.dp),
                                text = "Orders",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = Gilroy,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 2)
                        }
                        Row(
                            modifier = Modifier
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                tint = ColorAccent,
                                painter = painterResource(id = R.drawable.ic_feedback),
                                contentDescription = "Feedback")
                            Text(
                                modifier = Modifier
                                    .alpha(.8f)
                                    .padding(horizontal = 10.dp),
                                text = "Feedback",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = Gilroy,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 2)
                        }
                        Row(
                            modifier = Modifier
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                tint = ColorAccent,
                                painter = painterResource(id = R.drawable.ic_faq),
                                contentDescription = "FAQ")
                            Text(
                                modifier = Modifier
                                    .alpha(.8f)
                                    .padding(horizontal = 10.dp),
                                text = "FAQ",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                maxLines = 2)
                        }
                        Row(
                            modifier = Modifier
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                tint = ColorAccent,
                                painter = painterResource(id = R.drawable.ic_documents),
                                contentDescription = "Delivery and payment terms")
                            Text(
                                modifier = Modifier
                                    .alpha(.8f)
                                    .padding(horizontal = 10.dp),
                                text = "Delivery and payment terms",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = Gilroy,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 2)
                        }
                        Row(
                            modifier = Modifier
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                tint = ColorAccent,
                                painter = painterResource(id = R.drawable.ic_globe),
                                contentDescription = "Display language")
                            Text(
                                modifier = Modifier
                                    .alpha(.8f)
                                    .padding(horizontal = 10.dp),
                                text = "Display language",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = Gilroy,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 2)
                        }
                        Row(
                            modifier = Modifier
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                tint = ColorAccent,
                                painter = painterResource(id = R.drawable.ic_info),
                                contentDescription = "About us")
                            Text(
                                modifier = Modifier
                                    .alpha(.8f)
                                    .padding(horizontal = 10.dp),
                                text = "About us",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = Gilroy,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 2)
                        }
                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                ) {
                    Divider(
                        modifier = Modifier.alpha(.7f),
                        color = Color.LightGray)
                    Row(
                        modifier = Modifier
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            tint = ColorAccent,
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = "Logout")
                        Text(
                            modifier = Modifier
                                .alpha(.8f)
                                .padding(horizontal = 10.dp),
                            text = "Logout",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontFamily = Gilroy,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 2)
                    }
                }

            }
        }
    }
}

@Composable
@Preview
fun Preview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(ProfileScreenBGColor)){
        ProfileScreen()
    }
}