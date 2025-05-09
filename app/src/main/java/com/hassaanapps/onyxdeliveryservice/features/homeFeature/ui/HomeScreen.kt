package com.hassaanapps.onyxdeliveryservice.features.homeFeature.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.hassaanapps.onyxdeliveryservice.R
import com.hassaanapps.onyxdeliveryservice.features.languageScreenFeature.ui.LanguageSelectionScreen
import com.hassaanapps.onyxdeliveryservice.ui.theme.MontserratFontFamily
import com.hassaanapps.onyxdeliveryservice.ui.theme.PrimaryColor
import com.hassaanapps.onyxdeliveryservice.ui.theme.ShadowColor

@Composable
fun HomeScreen(modifier: Modifier = Modifier, deliveryName: String) {

    Column(modifier = modifier) {
        HomeScreenHeader(deliveryName)
        NewOthersTappedLayout(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(24.dp),
                    ambientColor = ShadowColor
                ),

            onItemSelected = {},
            defaultSelectedTab = TabItemSelect.NEW
        )

        EmptyDataScreen(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 60.dp)
                .align(Alignment.CenterHorizontally),
            title = "No orders yet",
            desc = "You don't have any orders in your history."
        )


    }
}

@Composable
fun HomeScreenHeader(
    deliveryName: String,
) {
    val deliveryNames = deliveryName.split(" ")

    val firstName = deliveryNames[0]
    val lastName = deliveryNames.drop(1).joinToString(separator = " ")

    val showLanguageDialog = remember { mutableStateOf(false) }
    val selectedLanguageNo = remember { mutableStateOf("1") }


    if (showLanguageDialog.value) {
        LanguageSelectionScreen(
            selectedLanguageNo.value,
            onDismiss = {
                showLanguageDialog.value = false
            },
            onLanguageSelected = {
                selectedLanguageNo.value = it
            })
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 150.dp, max = 170.dp)
            .background(color = Color((0xFFD42A0F)))
    ) {

        val (deliveryNameConstraint, topSubCircleCorner, deliveryImage) = createRefs()

        DeliveryNameText(
            modifier = Modifier
                .constrainAs(deliveryNameConstraint) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(deliveryNameConstraint.start)
                }
                .wrapContentSize(),
            firstName = firstName,
            lastName = lastName
        )


        LanguageIconWithCornerBackground(
            modifier = Modifier
                .constrainAs(topSubCircleCorner) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .wrapContentSize()
        ) {
            showLanguageDialog.value = true

        }

        Image(
            modifier = Modifier
                .constrainAs(deliveryImage) {
                    top.linkTo(parent.top)
                    start.linkTo(topSubCircleCorner.start)
                    end.linkTo(topSubCircleCorner.start)
                    bottom.linkTo(parent.bottom)
                    horizontalBias = .5f
                }
                .wrapContentSize(),
            contentScale = ContentScale.Fit,
            painter = painterResource(R.drawable.deliveryman),
            contentDescription = "Delivery Image",
        )
    }

}

@Composable
fun LanguageIconWithCornerBackground(
    modifier: Modifier = Modifier,
    onLanguageIconClicked: () -> Unit
) {
    Box(
        modifier = modifier,
        Alignment.TopEnd
    ) {

        Image(
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.ic_circle_top),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxHeight()
                .zIndex(1f),
            colorFilter = ColorFilter.tint(PrimaryColor),

            )

        IconButton(
            modifier = Modifier
                .zIndex(2f)
                .padding(top = 51.dp, end = 16.dp)
                .background(Color.White, RoundedCornerShape(10.dp)),
            onClick = onLanguageIconClicked
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_language),
                contentDescription = "Language icon",
                tint = PrimaryColor,
            )
        }

    }
}

@Composable
fun DeliveryNameText(modifier: Modifier, firstName: String, lastName: String) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = firstName,
            color = Color.White,
            fontFamily = MontserratFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            textAlign = TextAlign.End
        )
        Text(
            modifier = Modifier.wrapContentSize(),
            text = lastName,
            color = Color.White,
            fontFamily = MontserratFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            textAlign = TextAlign.End
        )
    }
}


@Composable
fun NewOthersTappedLayout(
    modifier: Modifier,
    onItemSelected: (TabItemSelect) -> Unit,
    defaultSelectedTab: TabItemSelect
) {

    var isNewSelected: Boolean by remember { mutableStateOf(defaultSelectedTab == TabItemSelect.NEW) }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))

    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()

        ) {

            TabItem(
                modifier = Modifier.weight(1f),
                text = "New",
                isSelected = isNewSelected,
                onTabClicked = {
                    onItemSelected(TabItemSelect.NEW)
                    isNewSelected = true
                }
            )

            TabItem(
                modifier = Modifier.weight(1f),
                "Others",
                isSelected = !isNewSelected
            ) {
                isNewSelected = false
                onItemSelected(TabItemSelect.NEW)
            }

        }
    }
}


@Composable
fun TabItem(
    modifier: Modifier,
    text: String,
    isSelected: Boolean,
    onTabClicked: () -> Unit
) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontFamily = MontserratFontFamily,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clickable { onTabClicked() }
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 32.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(50)),
        color = if (isSelected) Color.White else Color.Black
    )
}


@Composable
//@Preview(apiLevel = 33, showBackground = true)
private fun HomeScreenHeaderPreview() {

    HomeScreenHeader(
        deliveryName = "احمد عبدالقوي عبدالله حسان"
    )
}

//@Preview(apiLevel = 33, showBackground = true)
@Composable
private fun TabbedLayoutPReview() {
    NewOthersTappedLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onItemSelected = {},
        defaultSelectedTab = TabItemSelect.NEW
    )
}


@Preview(apiLevel = 33, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        deliveryName = "أحمد عبدالقوي محمد",
    )
}