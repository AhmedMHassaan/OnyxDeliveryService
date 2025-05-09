package com.hassaanapps.onyxdeliveryservice.features.homeFeature.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hassaanapps.onyxdeliveryservice.R
import com.hassaanapps.onyxdeliveryservice.ui.theme.MontserratFontFamily

@Composable
fun EmptyDataScreen(
    modifier: Modifier = Modifier,
    title: String,
    desc: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_emptyorder),
            contentDescription = "No data",
            modifier = Modifier.wrapContentSize()
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = title,
            fontFamily = MontserratFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description text with less bold weight
        Text(
            modifier = Modifier.fillMaxWidth(0.8f),
            text = desc,
            fontFamily = MontserratFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(apiLevel = 33, showBackground = true)
@Composable
private fun EmptyDataPreview() {
    EmptyDataScreen(
        modifier = Modifier.fillMaxSize(),
        title = "No Orders Yet",
        desc = "You don't have any orders in your history."
    )
}
