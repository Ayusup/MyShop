package com.moonborn.myshop.features.feature_planned_lists.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moonborn.myshop.R
import com.moonborn.myshop.features.feature_planned_lists.domain.model.PlannedList
import com.moonborn.myshop.features.feature_product.presentation.util.shadow
import com.moonborn.myshop.features.feature_product.presentation.screens.main_screen.productList
import com.moonborn.myshop.ui.theme.BGColor
import com.moonborn.myshop.ui.theme.ColorAccent
import com.moonborn.myshop.ui.theme.MRed
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
val plannedLists = listOf<PlannedList>(
    PlannedList(id = "", plannedListName = "For Palov", LocalDate.now(), productList()),
    PlannedList(id = "1", plannedListName = "For Palov", LocalDate.now(), productList()),
    PlannedList(id = "1", plannedListName = "For Palov", LocalDate.now(), productList()),
    PlannedList(id = "1", plannedListName = "For Palov", LocalDate.now(), productList()),
    PlannedList(id = "1", plannedListName = "For Palov", LocalDate.now(), productList())
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlannedListTopBar(
//    scrollBehaviour: TopAppBarScrollBehavior
    function: () -> Unit
){
    TopAppBar(
        title = {

        },
//        scrollBehavior = scrollBehaviour,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        navigationIcon = {},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically


            ){
                Box(modifier = Modifier.fillMaxWidth()){
                    Icon(painter = painterResource(id = R.drawable.person),
                        contentDescription = "profile button",
                        tint = ColorAccent,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterStart)
                            .clickable { function() }
                    )
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Planned lists",
                        fontSize = 20.sp,
                        color = ColorAccent
                    )

                }
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlannedListScreen(parentPadding: PaddingValues, function: () -> Unit){
    Box(modifier = Modifier.fillMaxSize().padding(parentPadding).background(BGColor)){
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp, end = 6.dp, top = 8.dp)){
            item {
                PlannedListTopBar(function)
            }
            items(plannedLists.size){ index ->
                val plannedList = plannedLists[index]
                PlannedListModel(name = plannedList.plannedListName, date = plannedList.date, products = plannedList.products)
            }
        }
        //FAB custom color
        FloatingActionButton(
            shape = CircleShape,
            modifier = Modifier
                .shadow(
                color = MRed,
                offsetY = (0).dp,
                offsetX = (0).dp,
                blurRadius = 8.dp,
                spread = .02.dp//Spread of the solid color, as small you make as visible is your blur
            )
                .align(Alignment.BottomCenter),
//                .padding(20.dp),
            onClick = {  },
            containerColor = MRed,
            contentColor = Color.White,
        ){
            Icon(Icons.Filled.Add,"")
        }
    }
}