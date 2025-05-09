package com.hassaanapps.onyxdeliveryservice.features.homeFeature.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.hassaanapps.onyxdeliveryservice.R
import com.hassaanapps.onyxdeliveryservice.ui.theme.MontserratFontFamily

@Composable
fun HomeScreen(modifier: Modifier = Modifier, deliveryName: String) {

    Column(modifier = modifier) {
        HomeScreenHeader(deliveryName)
    }
}

@Composable
fun HomeScreenHeader(
    deliveryName: String,
) {
    val deliveryNames = deliveryName.split(" ")


    val firstName = deliveryNames[0]

    Box(
        Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(color = Color((0xFFD42A0F))),
        contentAlignment = Alignment.TopStart,
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 30.dp)
        ) {
            Text(
                text = firstName,
                color = Color.White,
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 32.sp
            )
            Text(
                text = deliveryName.drop(firstName.length).trim(),
                color = Color.White,
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
        }

        Box(modifier = Modifier.fillMaxSize(), Alignment.TopEnd) {

            Image(
                contentScale = ContentScale.FillHeight,
                painter = painterResource(id = R.drawable.ic_circle_top),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxHeight()
                    .zIndex(1f),
                colorFilter = ColorFilter.tint(Color(0xFF004F62)),

                )

            IconButton(modifier = Modifier
                .zIndex(2f)
                .padding(top = 51.dp, end = 16.dp)
                .background(Color.White, RoundedCornerShape(10.dp)),
                onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_language),
                    contentDescription = "Language icon",
                    tint = Color(0xFF004F62),
                )
            }

        }

        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 70.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(R.drawable.deliveryman),
            contentDescription = "Delivery Image",
        )

    }
}

@Composable
private fun HomeScreenHeaderPreview() {

    HomeScreenHeader(
        deliveryName = "Ahmed Othman"
    )
}