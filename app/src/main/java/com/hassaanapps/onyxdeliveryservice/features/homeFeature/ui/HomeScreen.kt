package com.hassaanapps.onyxdeliveryservice.features.homeFeature.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hassaanapps.onyxdeliveryservice.R
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.CustomerAddress
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBill
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBillStatus
import com.hassaanapps.onyxdeliveryservice.features.languageScreenFeature.ui.LanguageSelectionScreen
import com.hassaanapps.onyxdeliveryservice.shared.ui.theme.StateDeliveredColor
import com.hassaanapps.onyxdeliveryservice.shared.ui.theme.MontserratFontFamily
import com.hassaanapps.onyxdeliveryservice.shared.ui.theme.PrimaryColor
import com.hassaanapps.onyxdeliveryservice.shared.ui.theme.ShadowColor
import com.hassaanapps.onyxdeliveryservice.shared.ui.theme.StateNewColor
import com.hassaanapps.onyxdeliveryservice.shared.ui.theme.StateReturnedColor
import com.hassaanapps.onyxdeliveryservice.shared.ui.theme.TagStatusTextColor
import java.util.Locale

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    deliveryName: String
) {

    val billsItems = homeViewModel.billItemsStateFlow.collectAsStateWithLifecycle().value
    val isLoading = homeViewModel.loadingState.collectAsStateWithLifecycle().value


    Column(modifier = modifier) {
        HomeScreenHeader(deliveryName)

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            NewOthersTappedLayout(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally)
                    .shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(24.dp),
                        ambientColor = ShadowColor
                    ),

                onItemSelected = {

                },
                defaultSelectedTab = TabItemSelect.NEW
            )


            billsItems?.let { bills ->
                if (bills.isEmpty()) {
                    EmptyDataScreen(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = 60.dp)
                            .align(Alignment.CenterHorizontally),
                        title = "No orders yet",
                        desc = "You don't have any orders in your history."
                    )

                } else {
                    BuildNewItems(bills)
                }
            }


        }
    }
}

@Composable
fun BuildNewItems(billsItems: List<DeliveryBill>) {
    BillsItemsListView(billsItems)
}

@Composable
fun BillsItemsListView(billsItems: List<DeliveryBill>) {
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        itemsIndexed(items = billsItems) { index, item ->

            BillItem(item)
        }
    }
}

@Composable
fun BillItem(bill: DeliveryBill) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 91.dp, 120.dp)
            .padding(top = 12.dp),

        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, ShadowColor),
        colors = CardDefaults.cardColors()
            .copy(containerColor = Color.White, contentColor = Color.White),
    ) {
        val valueTextColor = when (bill.deliveryStatusFlag) {
            DeliveryBillStatus.New -> StateNewColor
            DeliveryBillStatus.Delivered -> StateDeliveredColor
            DeliveryBillStatus.RETURNED -> StateReturnedColor
            else -> MaterialTheme.colorScheme.onSurface
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f)
                    .padding(start = 8.dp, bottom = 16.dp),
            ) {
                Text(
                    text = "#${bill.billNumber}",
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                ) {

                    BillItemSection(
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        keyTitle = "Status",
                        value = bill.deliveryStatusFlag?.name ?: "",
                        valueTextColor = valueTextColor,
                    )

                    Divider()

                    BillItemSection(
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        keyTitle = "Total price",
                        value = "${
                            String.format(
                                Locale.ENGLISH,
                                "%.2f",
                                bill.billAmount
                            )
                        } LE",
                        valueTextColor = valueTextColor,
                    )
                    Divider()

                    BillItemSection(
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        keyTitle = "Date",
                        value = bill.billDate ?: "",
                        valueTextColor = valueTextColor,
                    )


                }

            }

            OrderDetailsButtonSection(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                backgroundColor = valueTextColor,
            )
        }

    }
}

@Composable
fun OrderDetailsButtonSection(modifier: Modifier, backgroundColor: Color) {
    Column(
        modifier
            .background(
                color = backgroundColor
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Order\nDetails",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = "Details",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun Divider() {
    Box(
        modifier = Modifier
            .size(width = 1.dp, height = 50.dp)
            .background(Color(0xFFC7C7C7))
    )
}

@Composable
fun BillItemSection(
    modifier: Modifier,
    keyTitle: String,
    value: String,
    valueTextColor: Color
) {
    Column(modifier = modifier) {


        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = keyTitle,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = TagStatusTextColor
        )


        Text(
            modifier = Modifier
                .padding(top = 5.dp)
                .align(Alignment.CenterHorizontally),
            text = value,
            fontFamily = MontserratFontFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = valueTextColor,
        )


    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
private fun BillItemPreview() {
    BillItem(
        DeliveryBill(
            billAmount = 5.0f,
            billDate = "2011",
            billNumber = "123456",
            billSerial = "56454asdasd45654",
            billTime = "558:55",
            billType = "1",
            customerAddress = CustomerAddress(
                "aaa",
                "aaa",
                "aaa",
                "aaa"
            ),
            customerName = "Ahmed",
            deliveryAmount = "5",
            deliveryStatusFlag = DeliveryBillStatus.Delivered,
            latitude = "55.55",
            longitude = "55.55",
            mobileNumber = "01146906776",
            regionName = "Region",
            taxAmount = "5"
        )
    )
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
                .wrapContentHeight()

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
                onItemSelected(TabItemSelect.OTHER)
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
            .padding(horizontal = 32.dp, vertical = 12.dp)
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


/*
@Preview(apiLevel = 33, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        deliveryName = "أحمد عبدالقوي محمد",
        homeViewModel = koinInject(),
    )
}*/


